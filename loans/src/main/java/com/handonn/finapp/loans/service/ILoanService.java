package com.handonn.finapp.loans.service;

import com.handonn.finapp.loans.model.LoanDto;
import com.handonn.finapp.loans.model.LoanUpdatedDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanService {
    List<LoanDto> getAll();

    LoanDto getByLoanNumber(String loanNumber);

    void create(LoanDto loan);

    void update(String loanNumber, LoanUpdatedDto loanUpdatedDto);

    void delete(String loanNumber);
}
