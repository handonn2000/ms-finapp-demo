package com.handonn.finapp.loans.exception;

import com.handonn.finapp.common.exception.code.EInternalErrorCode;
import com.handonn.finapp.common.exception.definition.BusinessException;
import com.handonn.finapp.common.exception.definition.InternalException;

public class LoanException {
    public static class BusinessError extends BusinessException {
        public BusinessError(ELoanErrorCode errorCode) {
            super(errorCode.getCode(), errorCode.getDescription());
        }
    }

    public static class InternalError extends InternalException {
        public InternalError(EInternalErrorCode errorCode) {
            super(errorCode, "ACCOUNT SERVICE");
        }
    }
}
