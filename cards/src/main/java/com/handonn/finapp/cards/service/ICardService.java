package com.handonn.finapp.cards.service;


import com.handonn.finapp.cards.model.CardDto;
import org.springframework.stereotype.Service;

public interface ICardService {

    void create(CardDto cardDto);
}
