package com.handonn.finapp.accounts.exception;

import com.handonn.finapp.common.exception.code.EInternalErrorCode;
import com.handonn.finapp.common.exception.definition.BusinessException;
import com.handonn.finapp.common.exception.definition.InternalException;

public class AccountException {

    public static class BusinessError extends BusinessException {
        public BusinessError(EAccountErrorCode errorCode) {
            super(errorCode.getCode(), errorCode.getDescription());
        }
    }

    public static class InternalError extends InternalException {
        public InternalError(EInternalErrorCode errorCode) {
            super(errorCode, "ACCOUNT SERVICE");
        }
    }
}
