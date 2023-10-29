package com.handonn.finapp.accounts.exception.definition;

import com.handonn.finapp.accounts.exception.code.EInternalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InternalException extends RuntimeException {
    private EInternalErrorCode error;
}
