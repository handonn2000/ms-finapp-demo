package com.handonn.finapp.loans.controller;

import com.handonn.finapp.common.model.BaseResponse;
import com.handonn.finapp.loans.model.LoanDto;
import com.handonn.finapp.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/loans")
@RequiredArgsConstructor
@Validated
@Tag(name = "Loan Service", description = "CRUD for loan operation")
public class LoanController {

    private final ILoanService loanService;

    @GetMapping
    public BaseResponse<List<LoanDto>> getAllLoans() {
        List<LoanDto> loans = loanService.getAll();

        return BaseResponse.<List<LoanDto>>builder()
                .statusCode(HttpStatus.OK)
                .message("success")
                .data(loans)
                .build();
    }

    @GetMapping
    public BaseResponse<?> create(@RequestBody LoanDto loan) {
        loanService.create(loan);

        return BaseResponse.<List<LoanDto>>builder()
                .statusCode(HttpStatus.OK)
                .message("created")
                .build();
    }
}
