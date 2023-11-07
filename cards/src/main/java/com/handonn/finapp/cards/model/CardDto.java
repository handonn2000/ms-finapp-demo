package com.handonn.finapp.cards.model;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.common.validation.EnumValidator;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @NotEmpty(message = "Mobile Number can not be a null or empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "Mobile number must be 10 digits")
    private String mobileNumber;

    @NotEmpty
    @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits")
    private String cardNumber;

    @EnumValidator(enumClass = ECardType.class, message = "Card Type is not valid")
    private String cardType;

    @Positive(message = "Total card limit should be greater than zero")
    private BigDecimal totalLimit;

    @PositiveOrZero(message = "Used Amount should NOT be negative")
    private BigDecimal amountUsed;

    @PositiveOrZero(message = "Total available amount should be equal or greater than zero")
    private BigDecimal availableAmount;

    public static CardDto from(CardEntity card) {
        return CardDto.builder()
                .mobileNumber(card.getMobileNumber())
                .cardNumber(card.getCardNumber())
                .cardType(card.getCardType().name())
                .totalLimit(card.getTotalLimit())
                .amountUsed(card.getAmountUsed())
                .availableAmount(card.getAvailableAmount())
                .build();
    }

    public static CardEntity to(CardDto cardDto) {
        return CardEntity.builder()
                .mobileNumber(cardDto.getMobileNumber())
                .cardNumber(cardDto.getCardNumber())
                .cardType(ECardType.valueOf(cardDto.getCardType()))
                .totalLimit(cardDto.getTotalLimit())
                .amountUsed(cardDto.getAmountUsed())
                .availableAmount(cardDto.getAvailableAmount())
                .build();
    }

    public static void mapToCardEntity(CardDto cardDto, CardEntity cardEntity) {
        cardEntity.setMobileNumber(cardDto.getMobileNumber());
        cardEntity.setCardNumber(cardDto.getCardNumber());
        cardEntity.setCardType(ECardType.valueOf(cardDto.getCardType()));
        cardEntity.setAmountUsed(cardDto.getAmountUsed());
        cardEntity.setTotalLimit(cardDto.getTotalLimit());
        cardEntity.setAvailableAmount(cardDto.getAvailableAmount());
    }
}
