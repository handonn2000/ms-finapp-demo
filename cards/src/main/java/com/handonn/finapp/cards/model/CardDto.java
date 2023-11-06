package com.handonn.finapp.cards.model;

import com.handonn.finapp.cards.entity.CardEntity;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Getter @Setter @ToString
@Builder
@AllArgsConstructor @NoArgsConstructor
public class CardDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Account number must be 10 digits")
    private String mobileNumber;

    @NotEmpty
    @Pattern(regexp="(^$|[0-9]{12})",message = "CardNumber must be 12 digits")
    private String cardNumber;

    @NotEmpty
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private int totalLimit;

    @PositiveOrZero(message = "Used Amount should NOT be negative")
    private int amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private int availableAmount;

    public static CardDto from(CardEntity card) {
        return CardDto.builder()
                .mobileNumber(card.getMobileNumber())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }

    public static CardEntity to(CardDto cardDto) {
        return CardEntity.builder()
                .mobileNumber(cardDto.getMobileNumber())
                .cardNumber(cardDto.getCardNumber())
                .cardType(cardDto.getCardType())
                .totalLimit(cardDto.getTotalLimit())
                .amountUsed(cardDto.getAmountUsed())
                .availableAmount(cardDto.getAvailableAmount())
                .build();
    }
}
