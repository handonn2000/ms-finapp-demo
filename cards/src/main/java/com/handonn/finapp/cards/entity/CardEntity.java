package com.handonn.finapp.cards.entity;

import com.handonn.finapp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

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
    private String cardType;

    @Column(name = "total_limit")
    private int totalLimit;

    @Column(name = "amount_used")
    private int amountUsed;

    @Column(name = "available_amount")
    private int availableAmount;

}
