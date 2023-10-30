package com.handonn.finapp.common.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatusCode;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Getter
@Builder
public class BaseResponse<T> {

    private HttpStatusCode statusCode;
    private String message;

    @JsonInclude(NON_NULL)
    private T data;
}
