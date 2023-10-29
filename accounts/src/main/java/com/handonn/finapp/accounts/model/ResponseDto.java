package com.handonn.finapp.accounts.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
public class ResponseDto<T> {

    private HttpStatusCode statusCode;
    private String message;

    @JsonInclude(NON_NULL)
    private T data;
}
