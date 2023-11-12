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

import java.rmi.ConnectException;
import java.time.LocalDateTime;
import java.util.Arrays;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandling {

    private static final int STACK_TRACE_LIMIT = 5;

    @ExceptionHandler({BusinessException.class})
    public ErrorResponse handleBusinessError(BusinessException e, WebRequest webRequest) {
        this.logError(ErrorType.BUSINESS_ERROR, e, e.getDescription());

        return ErrorResponse.builder()
                .errorCode(e.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(e.getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({InternalException.class})
    public ErrorResponse handleInternalExceptions(InternalException e, WebRequest webRequest) {
        this.logError(ErrorType.INTERNAL_ERROR, e);

        return ErrorResponse.builder()
                .errorCode(e.getError().getCode())
                .endPoint(webRequest.getDescription(false))
                .description("[" + e.getServiceName() + "] " + e.getError().getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({ConnectException.class})
    public ErrorResponse handleConnectionExceptions(ConnectException e, WebRequest webRequest) {
        this.logError(ErrorType.CONNECTION_ERROR, e);

        return ErrorResponse.builder()
                .errorCode(EInternalErrorCode.CONNECTION_EXCEPTION.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(EInternalErrorCode.CONNECTION_EXCEPTION.getDescription())
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

        this.logError(ErrorType.INPUT_ERROR, e);
        return ErrorResponse.builder()
                .errorCode(ECommonErrorCode.INPUT_ARGUMENT_INVALID.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(sbDescription.toString())
                .errorTime(LocalDateTime.now())
                .build();
    }

    @ExceptionHandler({Exception.class, Error.class})
    public ErrorResponse handleOtherExceptions(Exception e, WebRequest webRequest) {
        this.logError(ErrorType.OTHERS_ERROR, e);

        return ErrorResponse.builder()
                .errorCode(EInternalErrorCode.OTHER_EXCEPTION.getCode())
                .endPoint(webRequest.getDescription(false))
                .description(EInternalErrorCode.OTHER_EXCEPTION.getDescription())
                .errorTime(LocalDateTime.now())
                .build();
    }

    private void logError(ErrorType errorType, Exception e, String... messages) {
        var stackTraces = Arrays.stream(e.getStackTrace())
                .limit(STACK_TRACE_LIMIT)
                .toList();

        StringBuilder errorSb = new StringBuilder();
        errorSb.append("[").append(errorType.name()).append("]: ");
        for (String message : messages) {
            errorSb.append(message);
            errorSb.append("\n");
        }
        errorSb.append(stackTraces);

        log.error(errorSb.toString());
    }
}
