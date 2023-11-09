package com.handonn.finapp.cards.controller;

import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.service.ICardService;
import com.handonn.finapp.common.model.BaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api/v1/cards")
@RequiredArgsConstructor
@Validated
public class CardController {

    private final ICardService cardService;

    @PostMapping
    public BaseResponse<?> create(@Valid @RequestBody CardDto cardDto) {
        cardService.create(cardDto);
        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .message("Created")
                .build();
    }

    @GetMapping
    public BaseResponse<List<CardDto>> getCardList(Pageable pageable) {
        List<CardDto> cardDtoList = cardService.getAllCard(pageable);
        return BaseResponse.<List<CardDto>>builder()
                .statusCode(HttpStatus.OK)
                .message("Success")
                .data(cardDtoList)
                .build();
    }

    @GetMapping("/{cardNumber}")
    public BaseResponse<CardDto> getCardDetail(@PathVariable
                                               @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits") String cardNumber) {
        CardDto result = cardService.getByCardNumber(cardNumber);
        return BaseResponse.<CardDto>builder()
                .statusCode(HttpStatus.OK)
                .message("Success")
                .data(result)
                .build();
    }

    @PutMapping("/{id}")
    public BaseResponse<?> updateCard(@PathVariable Long id,
                                      @Valid @RequestBody CardDto cardDto) {
        cardService.updateCard(id, cardDto);
        return BaseResponse.builder()
                .statusCode(HttpStatus.OK)
                .message("Updated")
                .build();
    }

    @DeleteMapping
    public BaseResponse<?> deleteCard(@RequestParam String mobileNumber) {
        cardService.deleteCard(mobileNumber);
        return BaseResponse.builder()
                .statusCode(HttpStatus.OK)
                .message("Deleted")
                .build();
    }
}
