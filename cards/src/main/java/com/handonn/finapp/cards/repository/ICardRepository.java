package com.handonn.finapp.cards.repository;

import com.handonn.finapp.cards.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICardRepository extends JpaRepository<CardEntity, Long> {
    boolean existsByCardNumber(String cardNumber);
}
