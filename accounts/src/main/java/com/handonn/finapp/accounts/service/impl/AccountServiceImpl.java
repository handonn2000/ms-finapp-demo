package com.handonn.finapp.accounts.service.impl;

import com.handonn.finapp.accounts.entity.AccountEntity;
import com.handonn.finapp.accounts.entity.CustomerEntity;

import com.handonn.finapp.accounts.exception.AccountException;
import com.handonn.finapp.accounts.exception.EAccountErrorCode;
import com.handonn.finapp.accounts.model.AccountDto;
import com.handonn.finapp.accounts.model.CustomerDto;
import com.handonn.finapp.accounts.model.EAccountType;
import com.handonn.finapp.accounts.repository.AccountRepository;
import com.handonn.finapp.accounts.repository.CustomerRepository;
import com.handonn.finapp.accounts.service.IAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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
            throw new AccountException.BusinessError(EAccountErrorCode.CUSTOMER_DUPLICATION);
        }

        customer = customerRepository.save(customer);
        AccountEntity account = this.createAccount(customer.getId());
        accountRepository.save(account);
    }

    @Override
    public CustomerDto getByMobileNumber(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new AccountException.BusinessError(EAccountErrorCode.CUSTOMER_NOT_FOUND)
        );
        var account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(
                () -> new AccountException.BusinessError(EAccountErrorCode.ACCOUNT_NOT_FOUND)
        );

        CustomerDto customerResponse = CustomerDto.from(customer);
        AccountDto accountDto = AccountDto.from(account);
        customerResponse.setAccountInfo(accountDto);

        return customerResponse;
    }

    @Override
    public void update(Long customerId, CustomerDto customerDto) {
        var customer = customerRepository.findById(customerId).orElseThrow(
                () -> new AccountException.BusinessError(EAccountErrorCode.CUSTOMER_NOT_FOUND)
        );
        var account = accountRepository.findByCustomerId(customer.getId()).orElseThrow(
                () -> new AccountException.BusinessError(EAccountErrorCode.ACCOUNT_NOT_FOUND)
        );

        CustomerDto.map(customer, customerDto);
        AccountDto.map(account, customerDto.getAccountInfo());

        customerRepository.save(customer);
        accountRepository.save(account);
    }

    @Override
    public void deleteByMobileNumber(String mobileNumber) {
        var customer = customerRepository.findByMobileNumber(mobileNumber).orElseThrow(
                () -> new AccountException.BusinessError(EAccountErrorCode.CUSTOMER_NOT_FOUND)
        );

        accountRepository.deleteByCustomerId(customer.getId());
        customerRepository.deleteById(customer.getId());
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
