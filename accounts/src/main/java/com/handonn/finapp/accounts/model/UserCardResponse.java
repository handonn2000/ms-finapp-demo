package com.handonn.finapp.accounts.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserCardResponse {

    private Long customerId;

    private String mobilePhone;

    private CardDto cardInfo;
}
