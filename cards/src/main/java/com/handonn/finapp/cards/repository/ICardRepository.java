package com.handonn.finapp.cards.repository;

import com.handonn.finapp.cards.entity.CardEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Long>, JpaSpecificationExecutor<CardEntity> {

    boolean existsByCardNumber(String cardNumber);

    boolean existsByMobileNumber(String mobileNumber);

    CardEntity getByMobileNumber(String mobileNumber);

    @Transactional
    @Modifying
    void deleteByMobileNumber(String mobileNumber);

}
