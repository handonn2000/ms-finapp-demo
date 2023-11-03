package com.handonn.finapp.common.exception.code;

import lombok.Getter;

@Getter
public enum ECommonErrorCode {
    INPUT_ARGUMENT_INVALID(1_000, "Invalid input"),
    ;

    private final int code;
    private final String description;

    ECommonErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
