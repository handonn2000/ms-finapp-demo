package com.handonn.finapp.accounts.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EAccountErrorCode {

    ACCOUNT_NOT_FOUND(2_000, "Account not found"),
    ACCOUNT_DUPLICATION(2_001, "Account duplicated"),
    CUSTOMER_NOT_FOUND(2_000, "Customer not found"),
    CUSTOMER_DUPLICATION(2_001, "Customer duplicated"),
    CUSTOMER_CARD_NOT_FOUND(2_001, "No card found for this customer"),
    ;

    private final int code;
    private final String description;
}
