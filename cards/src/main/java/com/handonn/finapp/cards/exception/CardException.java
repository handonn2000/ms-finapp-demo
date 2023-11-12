package com.handonn.finapp.cards.exception;

import com.handonn.finapp.common.exception.definition.BusinessException;

public class CardException {
    public static class BusinessError extends BusinessException {
        public BusinessError(ECardErrorCode eCode) {
            super(eCode.getStatusCode(), eCode.getDescription());
        }
    }
}
