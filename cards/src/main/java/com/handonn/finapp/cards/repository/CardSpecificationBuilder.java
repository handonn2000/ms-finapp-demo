package com.handonn.finapp.cards.repository;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.cards.model.ECardType;
import org.springframework.data.jpa.domain.Specification;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class CardSpecificationBuilder {
    private static List<Specification<CardEntity>> specList;

    public CardSpecificationBuilder() {
        specList = new ArrayList<>();
    }

    public CardSpecificationBuilder cardTypeEqual(List<ECardType> cardType) {
        if (cardType != null) {
            Specification<CardEntity> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.and(root.get("cardType").in(cardType));
            specList.add(spec);
        }
        return this;
    }

    public CardSpecificationBuilder byTotalAmountGreaterThanEqual(BigDecimal amount) {
        if (amount != null) {
            Specification<CardEntity> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("totalLimit"), amount);
            specList.add(spec);
        }
        return this;
    }

    public CardSpecificationBuilder byTotalAmountLessThanEqual(BigDecimal amount) {
        if (amount != null) {
            Specification<CardEntity> spec = (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("totalLimit"), amount);
            specList.add(spec);
        }
        return this;
    }


    public Specification<CardEntity> build() {
        return Specification.allOf(specList);
    }
}
