package com.handonn.finapp.cards.entity;

import com.handonn.finapp.cards.model.ECardType;
import com.handonn.finapp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "cards")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CardEntity extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "mobile_number")
    private String mobileNumber;

    @Column(name = "card_number")
    private String cardNumber;

    @Column(name = "card_type")
    @Enumerated(EnumType.STRING)
    private ECardType cardType;

    @Column(name = "total_limit")
    private BigDecimal totalLimit;

    @Column(name = "amount_used")
    private BigDecimal amountUsed;

    @Column(name = "available_amount")
    private BigDecimal availableAmount;

}
