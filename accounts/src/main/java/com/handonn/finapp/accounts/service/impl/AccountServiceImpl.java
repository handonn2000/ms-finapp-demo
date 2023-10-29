package com.handonn.finapp.accounts.service.impl;

import com.handonn.finapp.accounts.entity.AccountEntity;
import com.handonn.finapp.accounts.entity.CustomerEntity;
import com.handonn.finapp.accounts.exception.code.EBusinessErrorCode;
import com.handonn.finapp.accounts.exception.definition.BusinessException;
import com.handonn.finapp.accounts.model.AccountDto;
import com.handonn.finapp.accounts.model.CustomerDto;
import com.handonn.finapp.accounts.model.EAccountType;
import com.handonn.finapp.accounts.repository.AccountRepository;
import com.handonn.finapp.accounts.repository.CustomerRepository;
import com.handonn.finapp.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements IAccountService {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;

    @Override
    public void create(CustomerDto customerDto) {
        CustomerEntity customer = new CustomerEntity();
        CustomerDto.map(customer, customerDto);
        boolean isDuplicate =
                customerRepository.existsByMobileNumber(customer.getMobileNumber())
                        && customerRepository.existsByEmail(customer.getEmail());
        if (isDuplicate) {
            throw new BusinessException(EBusinessErrorCode.CUSTOMER_DUPLICATION);
        }

        customer = customerRepository.save(customer);
        AccountEntity account = this.createAccount(customer.getCustomerId());
        accountRepository.save(account);
    }

    @Override
    public CustomerDto getByMobileNumber(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new BusinessException(EBusinessErrorCode.CUSTOMER_NOT_FOUND)
        );
        var account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new BusinessException(EBusinessErrorCode.ACCOUNT_NOT_FOUND)
        );

        CustomerDto customerResponse = CustomerDto.from(customer);
        AccountDto accountDto = AccountDto.from(account);
        customerResponse.setAccountInfo(accountDto);

        return customerResponse;
    }

    @Override
    public void update(Long customerId, CustomerDto customerDto) {
        var customer = customerRepository.findByCustomerId(customerId).orElseThrow(
                () -> new BusinessException(EBusinessErrorCode.CUSTOMER_NOT_FOUND)
        );
        var account = accountRepository.findByCustomerId(customer.getCustomerId()).orElseThrow(
                () -> new BusinessException(EBusinessErrorCode.ACCOUNT_NOT_FOUND)
        );

        CustomerDto.map(customer, customerDto);
        AccountDto.map(account, customerDto.getAccountInfo());

        customerRepository.save(customer);
        accountRepository.save(account);
    }

    @Override
    public void deleteByMobileNumber(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new BusinessException(EBusinessErrorCode.CUSTOMER_NOT_FOUND)
        );

        accountRepository.deleteByCustomerId(customer.getCustomerId());
        customerRepository.deleteById(customer.getCustomerId());
    }

    private AccountEntity createAccount(Long customerId) {
        var uuid = UUID.randomUUID();
        AccountEntity account = new AccountEntity();
        account.setAccountNumber(uuid.toString());
        account.setCustomerId(customerId);
        account.setAccountType(EAccountType.NORMAL);
        account.setBranchAddress("Address 1");

        return account;
    }
}
