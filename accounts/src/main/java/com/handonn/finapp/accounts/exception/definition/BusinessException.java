package com.handonn.finapp.accounts.exception.definition;

import com.handonn.finapp.accounts.exception.code.EBusinessErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private EBusinessErrorCode error;
}
