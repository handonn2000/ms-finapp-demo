package com.handonn.finapp.common.exception.definition;

import com.handonn.finapp.common.exception.code.EBusinessErrorCode;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private EBusinessErrorCode error;
}
