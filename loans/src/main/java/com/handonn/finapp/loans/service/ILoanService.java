package com.handonn.finapp.loans.service;

import com.handonn.finapp.loans.model.LoanDto;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILoanService {
    List<LoanDto> getAll();

    void create(LoanDto loan);
}
