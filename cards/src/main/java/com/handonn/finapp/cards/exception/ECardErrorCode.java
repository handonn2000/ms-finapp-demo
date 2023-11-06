package com.handonn.finapp.cards.exception;


import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECardErrorCode {

    CARD_NOT_FOUND(3_001, "Card number is not existed");

    private final int statusCode;
    private final String description;
}
