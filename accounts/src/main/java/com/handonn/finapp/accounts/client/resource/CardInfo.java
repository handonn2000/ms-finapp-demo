package com.handonn.finapp.accounts.client.resource;

import com.handonn.finapp.accounts.model.CardDto;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

@Data
@Builder
public class CardInfo implements Serializable {

    private String statusCode;
    private String message;
    private CardDto data;
}
