package com.handonn.finapp.common.exception;

import com.handonn.finapp.common.exception.code.EBusinessErrorCode;
import com.handonn.finapp.common.exception.code.EInternalErrorCode;
import com.handonn.finapp.common.exception.definition.BusinessException;
import com.handonn.finapp.common.exception.definition.InternalException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class GlobalExceptionHandling {

    @ExceptionHandler({BusinessException.class})
    public ErrorResponse handleBusinessError(BusinessException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .errorCode(e.getError().getCode())
                .endPoint(webRequest.getDescription(false))
                .description(e.getError().getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({InternalException.class})
    public ErrorResponse handleInternalExceptions(InternalException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .errorCode(e.getError().getCode())
                .endPoint(webRequest.getDescription(false))
                .description(e.getError().getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({MethodArgumentNotValidException.class})
    public ErrorResponse handleValidationExceptions(MethodArgumentNotValidException e, WebRequest webRequest) {
        StringBuilder sbDescription = new StringBuilder();
        sbDescription.append(EBusinessErrorCode.INPUT_ARGUMENT_INVALID.getDescription());
        sbDescription.append(": ");
        e.getAllErrors().forEach(error -> {
            sbDescription.append(error.getDefaultMessage());
            sbDescription.append(", ");
        });

        return ErrorResponse.builder()
                .errorCode(EBusinessErrorCode.INPUT_ARGUMENT_INVALID.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(sbDescription.toString())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({Exception.class})
    public ErrorResponse handleOtherExceptions(Exception e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .errorCode(EInternalErrorCode.OTHER_EXCEPTION.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(EInternalErrorCode.OTHER_EXCEPTION.getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }
}
