package com.handonn.finapp.common.exception.definition;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class BusinessException extends RuntimeException {

    private int code;
    private String description;
}
