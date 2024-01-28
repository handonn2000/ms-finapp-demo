package com.handonn.finapp.cards.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.ws.rs.DefaultValue;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.Sort;

import java.math.BigDecimal;
import java.util.List;

@Builder
@Getter
@Setter
@JsonIgnoreProperties(ignoreUnknown = true)
public class CardCriteria {

    private String mobileNumber;

    private List<ECardType> cardTypes;

    private BigDecimal minTotalAmount;

    private BigDecimal maxTotalAmount;

    private int offset = 0;

    private int size = 10;

    private String[] sortBy;

    @DefaultValue("ASC")
    private Sort.Direction direction;
}
