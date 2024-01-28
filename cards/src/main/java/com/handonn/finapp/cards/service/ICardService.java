package com.handonn.finapp.cards.service;


import com.handonn.finapp.cards.model.CardCriteria;
import com.handonn.finapp.cards.model.CardDto;
import org.springframework.data.domain.Page;

public interface ICardService {

    void create(CardDto cardDto);

    Page<CardDto> getAllCard(CardCriteria criteria);

    CardDto getByMobilePhone(String mobilePhone);

    void updateCard(Long id, CardDto cardDto);

    void deleteCard(String mobileNumber);

}
