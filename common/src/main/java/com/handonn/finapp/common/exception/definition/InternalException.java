package com.handonn.finapp.common.exception.definition;

import com.handonn.finapp.common.exception.code.EInternalErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class InternalException extends RuntimeException {
    private EInternalErrorCode error;
}
