package com.handonn.finapp.cards.service;


import com.handonn.finapp.cards.model.CardDto;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICardService {

    void create(CardDto cardDto);

    List<CardDto> getAllCard(Pageable pageable);

    CardDto getByCardNumber(String cardNumber);

    void updateCard(Long id, CardDto cardDto);

    void deleteCard(String mobileNumber);

}
