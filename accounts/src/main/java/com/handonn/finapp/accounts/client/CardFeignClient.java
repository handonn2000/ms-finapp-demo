package com.handonn.finapp.accounts.client;

import com.handonn.finapp.accounts.client.resource.CardResponse;
import com.handonn.finapp.accounts.model.CardDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cards")
public interface CardFeignClient {

    @GetMapping(path = "/api/v1/cards/details", consumes = "application/json")
    CardResponse<CardDto> getCardByMobilePhone(@RequestParam("mobilePhone") String mobilePhone);
}
