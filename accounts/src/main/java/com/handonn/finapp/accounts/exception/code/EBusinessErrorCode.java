package com.handonn.finapp.accounts.exception.code;

import lombok.Getter;

@Getter
public enum EBusinessErrorCode {

    INPUT_ARGUMENT_INVALID(1_000, "Input Argument is invalid"),
    CUSTOMER_DUPLICATION(2_000, "Customer already exist"),
    CUSTOMER_NOT_FOUND(2_001, "Customer not found"),
    ACCOUNT_DUPLICATION(3_000, "Account already exist"),
    ACCOUNT_NOT_FOUND(3_001, "Account not found"),
    ;

    private final int code;
    private final String description;

    EBusinessErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
