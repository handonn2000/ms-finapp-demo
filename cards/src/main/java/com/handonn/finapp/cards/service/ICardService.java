package com.handonn.finapp.cards.service;


import com.handonn.finapp.cards.model.CardDto;
import org.springframework.stereotype.Service;

import java.util.List;

public interface ICardService {

    void create(CardDto cardDto);

    List<CardDto> getAllCard();

    CardDto getByCardNumber(String cardNumber);
}
