package com.handonn.finapp.accounts.exception.code;

import lombok.Getter;

@Getter
public enum EInternalErrorCode {
    PARSING_EXCEPTION(0_001, "Parsing Error"),
    OTHER_EXCEPTION(0_000, "Internal Error"),
    ;

    private final int code;
    private final String description;

    EInternalErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
