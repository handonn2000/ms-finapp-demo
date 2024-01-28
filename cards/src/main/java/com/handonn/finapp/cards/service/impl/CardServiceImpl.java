package com.handonn.finapp.cards.service.impl;

import com.handonn.finapp.cards.entity.CardEntity;
import com.handonn.finapp.cards.exception.CardException;
import com.handonn.finapp.cards.exception.ECardErrorCode;
import com.handonn.finapp.cards.model.CardCriteria;
import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.repository.CardSpecificationBuilder;
import com.handonn.finapp.cards.repository.ICardRepository;
import com.handonn.finapp.cards.service.ICardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

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
    public Page<CardDto> getAllCard(CardCriteria criteria) {
        Sort sort = Sort.by(criteria.getDirection(), criteria.getSortBy());
        Pageable pageable = PageRequest.of(criteria.getOffset(), criteria.getSize(), sort);
        Specification<CardEntity> specs = new CardSpecificationBuilder()
                .mobileNumberEqual(criteria.getMobileNumber())
                .cardTypeIn(criteria.getCardTypes())
                .totalAmountGreaterThanEqual(criteria.getMinTotalAmount())
                .totalAmountLessThanEqual(criteria.getMaxTotalAmount())
                .build();
        Page<CardEntity> entityList = cardRepo.findAll(specs, pageable);
        return entityList.map(CardDto::from);
    }

    @Override
    public CardDto getByCardNumber(String cardNumber) {
        boolean existed = cardRepo.existsByCardNumber(cardNumber);
        if (!existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        CardEntity card = cardRepo.getByMobileNumber(cardNumber);
        return CardDto.from(card);
    }

    @Override
    public CardDto getByMobilePhone(String mobilePhone) {
        boolean existed = cardRepo.existsByMobileNumber(mobilePhone);
        if (!existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        CardEntity card = cardRepo.getByMobileNumber(mobilePhone);
        return CardDto.from(card);
    }

    @Override
    public void updateCard(Long id, CardDto cardDto) {
        CardEntity cardEntity = cardRepo.findById(id).orElseThrow(
                () -> new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND)
        );

        CardDto.mapToCardEntity(cardDto, cardEntity);
        cardRepo.save(cardEntity);
    }

    @Override
    public void deleteCard(String mobileNumber) {
        boolean existed = cardRepo.existsByMobileNumber(mobileNumber);
        if (!existed) {
            throw new CardException.BusinessError(ECardErrorCode.CARD_NOT_FOUND);
        }

        cardRepo.deleteByMobileNumber(mobileNumber);
    }

}
