package com.handonn.finapp.common.exception;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter @Builder
public class ErrorResponse {

    private int errorCode;
    private String endPoint;
    private String description;
    private LocalDateTime errorTime;
}
