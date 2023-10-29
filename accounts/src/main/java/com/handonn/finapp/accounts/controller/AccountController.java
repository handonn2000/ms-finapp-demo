package com.handonn.finapp.accounts.controller;

import com.handonn.finapp.accounts.model.CustomerDto;
import com.handonn.finapp.accounts.model.ResponseDto;
import com.handonn.finapp.accounts.service.IAccountService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/accounts")
@RequiredArgsConstructor
@Validated
@Tag(name = "Account Service", description = "CRUD for account operation")
public class AccountController {

    private final IAccountService accountService;

    @PostMapping
    public ResponseDto<?> create(@RequestBody @Valid CustomerDto customerDto) {
        accountService.create(customerDto);

        return ResponseDto.builder()
                .statusCode(HttpStatus.CREATED)
                .message("success")
                .build();
    }

    @GetMapping
    public ResponseDto<CustomerDto> getByMobileNumber(@RequestParam String mobileNumber) {
        CustomerDto customer = accountService.getByMobileNumber(mobileNumber);

        return ResponseDto.<CustomerDto> builder()
                .statusCode(HttpStatus.OK)
                .message("success")
                .data(customer)
                .build();
    }

    @PutMapping("/customer/{customerId}")
    public ResponseDto<?> update(@PathVariable Long customerId, @RequestBody @Valid CustomerDto customerDto) {
        accountService.update(customerId, customerDto);

        return ResponseDto.builder()
                .statusCode(HttpStatus.OK)
                .message("success")
                .build();
    }

    @DeleteMapping
    public ResponseDto<?> delete(@RequestParam String mobileNumber) {
        accountService.deleteByMobileNumber(mobileNumber);

        return ResponseDto.<CustomerDto> builder()
                .statusCode(HttpStatus.ACCEPTED)
                .message("success")
                .build();
    }
}
