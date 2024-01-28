package com.handonn.finapp.accounts.service;

import com.handonn.finapp.accounts.model.CustomerDto;
import com.handonn.finapp.accounts.model.UserCardResponse;

public interface IAccountService {

    void create(CustomerDto customerDto);

    CustomerDto getByMobileNumber(String mobileNumber);

    void update(Long customerId, CustomerDto customerDto);

    void deleteByMobileNumber(String mobileNumber);

    UserCardResponse findCustomerCardInfoByMobilePhone(String mobilePhone);
}
