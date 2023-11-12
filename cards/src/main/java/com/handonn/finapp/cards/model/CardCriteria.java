package com.handonn.finapp.cards.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
public class CardCriteria {
    private List<ECardType> cardTypes;

    private BigDecimal minTotalAmount;

    private BigDecimal maxTotalAmount;

    private int offset;

    private int size;

    private String[] sortBy;

    private Sort.Direction direction;
}
