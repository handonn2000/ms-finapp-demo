package com.handonn.finapp.cards.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum ECardType {
    VISA,
    MASTERCARD,
    AMERICAN_EXPRESS,
    DISCOVER,
    JCB,
    DINERS_CLUB,
    UNKNOWN;
}
