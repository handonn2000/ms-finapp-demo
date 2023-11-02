package com.handonn.finapp.loans.service.impl;

import com.handonn.finapp.common.exception.definition.BusinessException;
import com.handonn.finapp.loans.entity.LoanEntity;
import com.handonn.finapp.loans.model.LoanDto;
import com.handonn.finapp.loans.repository.LoanRepository;
import com.handonn.finapp.loans.service.ILoanService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;

    @Override
    public List<LoanDto> getAll() {
        return loanRepository.findAll().stream()
                .map(LoanDto::from)
                .collect(Collectors.toList());
    }

    @Override
    public void create(LoanDto loan) {
        LoanEntity loanEntity = new LoanEntity();
        boolean isExisted = loanRepository.existsByLoanNumber(loan.getLoanNumber());
        if (isExisted) {
            throw new BusinessException(E);
        }

        LoanDto.map(loanEntity, loan);
        loanRepository.save(loanEntity);
    }
}
