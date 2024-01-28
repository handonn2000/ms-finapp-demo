package com.handonn.finapp.accounts.client.resource;


import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class CardResponse<T> {

    private String statusCode;
    private String message;
    private T data;
}
