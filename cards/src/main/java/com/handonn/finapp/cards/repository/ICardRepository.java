package com.handonn.finapp.cards.repository;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.cards.model.ECardType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {
    boolean existsByCardNumber(String cardNumber);

    boolean existsByMobileNumber(String mobileNumber);

    CardEntity getByCardNumber(String cardNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);

    interface Specifications {
        static Specification<CardEntity> byCardType(ECardType cardType) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.equal(root.get("cardType"), cardType);
        }

        static Specification<CardEntity> byTotalAmountGreaterThanEqual(BigDecimal amount) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.greaterThanOrEqualTo(root.get("amountTotal"), amount);
        }

        static Specification<CardEntity> byTotalAmountLessThanEqual(BigDecimal amount) {
            return (root, query, criteriaBuilder) ->
                    criteriaBuilder.lessThanOrEqualTo(root.get("amountTotal"), amount);
        }
    }
}
