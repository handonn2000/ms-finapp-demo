package com.handonn.finapp.cards.service.impl;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.cards.exception.CardException;
import com.handonn.finapp.cards.exception.ECardErrorCode;
import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.repository.ICardRepository;
import com.handonn.finapp.cards.service.ICardService;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final ICardRepository cardRepo;

    @Override
    public void create(CardDto cardDto) {
        // Check duplicated
        boolean existed = cardRepo.existsByCardNumber(cardDto.getCardNumber());
        if (existed) {

            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        // if not existed
        CardEntity card = CardDto.to(cardDto);
        cardRepo.save(card);
    }
}
