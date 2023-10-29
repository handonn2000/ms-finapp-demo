package com.handonn.finapp.accounts.exception;

import com.handonn.finapp.accounts.exception.code.EBusinessErrorCode;
import com.handonn.finapp.accounts.exception.code.EInternalErrorCode;
import com.handonn.finapp.accounts.exception.definition.BusinessException;
import com.handonn.finapp.accounts.exception.definition.InternalException;
import org.hibernate.query.sqm.ParsingException;
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

    @ExceptionHandler({ParsingException.class})
    public ErrorResponse handleParsingExceptions(ParsingException e, WebRequest webRequest) {
        return ErrorResponse.builder()
                .errorCode(EInternalErrorCode.PARSING_EXCEPTION.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(EInternalErrorCode.PARSING_EXCEPTION.getDescription())
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
