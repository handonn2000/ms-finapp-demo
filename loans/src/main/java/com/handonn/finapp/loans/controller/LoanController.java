package com.handonn.finapp.loans.controller;

import com.handonn.finapp.common.model.BaseResponse;
import com.handonn.finapp.loans.model.LoanDto;
import com.handonn.finapp.loans.model.LoanUpdatedDto;
import com.handonn.finapp.loans.service.ILoanService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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


    @GetMapping("/{loanNumber}")
    public BaseResponse<LoanDto> getByLoanNumber(@PathVariable String loanNumber) {
        LoanDto loan = loanService.getByLoanNumber(loanNumber);

        return BaseResponse.<LoanDto>builder()
                .statusCode(HttpStatus.OK)
                .message("success")
                .data(loan)
                .build();
    }

    @PostMapping
    public BaseResponse<?> create(@RequestBody @Valid LoanDto loan) {
        loanService.create(loan);

        return BaseResponse.<List<LoanDto>>builder()
                .statusCode(HttpStatus.CREATED)
                .message("created")
                .build();
    }

    @PutMapping("/{loanNumber}")
    public BaseResponse<?> update(@PathVariable String loanNumber,
                                  @RequestBody @Valid LoanUpdatedDto loanUpdatedDto) {
        loanService.update(loanNumber, loanUpdatedDto);

        return BaseResponse.<List<LoanDto>>builder()
                .statusCode(HttpStatus.ACCEPTED)
                .message("updated")
                .build();
    }

    @DeleteMapping("/{loanNumber}")
    public BaseResponse<?> delete(@PathVariable String loanNumber) {
        loanService.delete(loanNumber);

        return BaseResponse.<List<LoanDto>>builder()
                .statusCode(HttpStatus.ACCEPTED)
                .message("deleted")
                .build();
    }
}
