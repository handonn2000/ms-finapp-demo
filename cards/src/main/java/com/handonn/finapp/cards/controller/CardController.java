package com.handonn.finapp.cards.controller;

import com.handonn.finapp.cards.model.CardDto;
import com.handonn.finapp.cards.service.ICardService;
import com.handonn.finapp.common.model.BaseResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import lombok.RequiredArgsConstructor;
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

    @PostMapping("/new-card")
    public BaseResponse<?> create(@Valid @RequestBody CardDto cardDto) {
        cardService.create(cardDto);
        return BaseResponse.builder()
                .statusCode(HttpStatus.CREATED)
                .message("Created")
                .build();
    }

    @GetMapping("/card-list")
    public BaseResponse<List<CardDto>> getCardList() {
        List<CardDto> cardDtoList = cardService.getAllCard();
        return BaseResponse.<List<CardDto>>builder()
                .statusCode(HttpStatus.OK)
                .message("Success")
                .data(cardDtoList)
                .build();
    }

    @GetMapping("/card-detail")
    public BaseResponse<CardDto> getCardDetail(@RequestParam
                                               @Pattern(regexp = "(^$|[0-9]{12})", message = "CardNumber must be 12 digits") String cardNumber) {
        CardDto result = cardService.getByCardNumber(cardNumber);
        return BaseResponse.<CardDto>builder()
                .statusCode(HttpStatus.OK)
                .message("Success")
                .data(result)
                .build();
    }

    @PutMapping("/card/{id}")
    public BaseResponse<?> updateCard(@PathVariable Long id,
                                      @Valid @RequestBody CardDto cardDto) {
        boolean result = cardService.updateCard(id, cardDto);
        if (!result) {
            return BaseResponse.builder()
                    .statusCode(HttpStatus.EXPECTATION_FAILED)
                    .message("Updated Failed")
                    .build();
        }

        return BaseResponse.builder()
                .statusCode(HttpStatus.OK)
                .message("Updated")
                .build();
    }

    @DeleteMapping("/card/{mobileNumber}")
    public BaseResponse<?> deleteCard(@PathVariable String mobileNumber) {
        boolean result = cardService.deleteCard(mobileNumber);
        if (!result) {
            return BaseResponse.builder()
                    .statusCode(HttpStatus.EXPECTATION_FAILED)
                    .message("Deleted Failed")
                    .build();
        }

        return BaseResponse.builder()
                .statusCode(HttpStatus.OK)
                .message("Deleted")
                .build();
    }
}
