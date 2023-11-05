package com.handonn.finapp.cards.controller;

import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.service.ICardService;
import com.handonn.finapp.common.model.BaseResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final ICardService cardService;

    @PostMapping(name = "/create")
    public BaseResponse<?> create(@Valid @RequestBody CardDto cardDto) {
        cardService.create(cardDto);
        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .message("Created")
                .build();
    }

}
