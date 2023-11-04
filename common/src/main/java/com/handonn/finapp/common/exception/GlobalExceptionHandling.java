package com.handonn.finapp.common.exception;

import com.handonn.finapp.common.exception.code.ECommonErrorCode;
import com.handonn.finapp.common.exception.code.EInternalErrorCode;
import com.handonn.finapp.common.exception.definition.BusinessException;
import com.handonn.finapp.common.exception.definition.InternalException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandling {

    @ExceptionHandler({BusinessException.class})
    public ErrorResponse handleBusinessError(BusinessException e, WebRequest webRequest) {
        log.error("[BUSINESS_ERROR]: {}", e.getMessage());

        return ErrorResponse.builder()
                .errorCode(e.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(e.getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({InternalException.class})
    public ErrorResponse handleInternalExceptions(InternalException e, WebRequest webRequest) {
        log.error("[INTERNAL_ERROR]: {}", e.getMessage());

        return ErrorResponse.builder()
                .errorCode(e.getError().getCode())
                .endPoint(webRequest.getDescription(false))
                .description("[" + e.getServiceName() + "] " + e.getError().getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e, WebRequest webRequest) {
        StringBuilder sbDescription = new StringBuilder();
        sbDescription.append(ECommonErrorCode.INPUT_ARGUMENT_INVALID.getDescription());
        sbDescription.append(": ");
        e.getAllErrors().forEach(error -> {
            sbDescription.append(error.getDefaultMessage());
            sbDescription.append(", ");
        });

        log.error("[ARGUMENT_ERROR]: {}", e.getMessage());
        return ErrorResponse.builder()
                .errorCode(ECommonErrorCode.INPUT_ARGUMENT_INVALID.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(sbDescription.toString())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({Exception.class})
    public ErrorResponse handleOtherExceptions(Exception e, WebRequest webRequest) {
        log.error("[SYSTEM_ERROR]: {}", e.getMessage());

        return ErrorResponse.builder()
                .errorCode(EInternalErrorCode.OTHER_EXCEPTION.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(EInternalErrorCode.OTHER_EXCEPTION.getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }
}
