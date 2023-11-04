package com.handonn.finapp.loans.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ELoanErrorCode {
    LOAN_NOT_FOUND(2_000, "Loan not found"),
    LOAN_DUPLICATION(2_001, "Loan duplicated"),
    ;

    private final int code;
    private final String description;
}
