package com.handonn.finapp.cards.service.impl;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.cards.exception.CardException;
import com.handonn.finapp.cards.exception.ECardErrorCode;
import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.repository.ICardRepository;
import com.handonn.finapp.cards.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CardServiceImpl implements ICardService {

    private final ICardRepository cardRepo;

    @Override
    public void create(CardDto cardDto) {
        boolean existed = cardRepo.existsByCardNumber(cardDto.getCardNumber());
        if (existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_ALREADY_EXISTED);
        }

        CardEntity card = CardDto.to(cardDto);
        cardRepo.save(card);
    }

    @Override
    public List<CardDto> getAllCard() {
        List<CardEntity> entityList = cardRepo.findAll();
        List<CardDto> cardDtoList = new ArrayList<>();
        entityList.forEach(record -> {
            cardDtoList.add(CardDto.from(record));
        });

        return cardDtoList;
    }

    @Override
    public CardDto getByCardNumber(String cardNumber) {
        boolean existed = cardRepo.existsByCardNumber(cardNumber);
        if (!existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        CardEntity card = cardRepo.getByCardNumber(cardNumber);
        return CardDto.from(card);
    }

    @Override
    public boolean updateCard(Long id, CardDto cardDto) {
        CardEntity cardEntity = cardRepo.findById(id).orElseThrow(
                () -> new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND)
        );
        if (cardEntity == null) {
            return false;
        }

        CardDto.mapToCardEntity(cardDto, cardEntity);
        cardRepo.save(cardEntity);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        boolean existed = cardRepo.existsByMobileNumber(mobileNumber);
        if (!existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        cardRepo.deleteByMobileNumber(mobileNumber);
        return true;
    }

}
