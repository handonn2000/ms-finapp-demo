package com.handonn.finapp.common.exception.code;

import lombok.Getter;

@Getter
public enum EInternalErrorCode {
    PARSING_EXCEPTION(0_000, "Parsing Error"),
    OTHER_EXCEPTION(0_001, "Internal Error"),
    IO_EXCEPTION(0_002, "IO Error"),
    CONNECTION_EXCEPTION(0_003, "Service Unavailable"),
    ;

    private final int code;
    private final String description;

    EInternalErrorCode(int code, String description) {
        this.code = code;
        this.description = description;
    }
}
