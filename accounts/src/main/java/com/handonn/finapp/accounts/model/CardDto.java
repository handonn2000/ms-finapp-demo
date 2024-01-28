package com.handonn.finapp.accounts.model;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class CardDto {

    private String mobileNumber;
    private String cardNumber;
    private String cardType;
    private BigDecimal totalLimit;
    private BigDecimal amountUsed;
    private BigDecimal availableAmount;
}
