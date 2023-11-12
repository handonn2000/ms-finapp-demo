package com.handonn.finapp.cards.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum ECardErrorCode {

    CARD_NOT_FOUND(3_001, "Card number is not existed"),
    CARD_ALREADY_EXISTED(3_002, "Card number is already existed");

    private final int statusCode;
    private final String description;
}
