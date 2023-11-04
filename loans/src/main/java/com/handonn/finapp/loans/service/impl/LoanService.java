package com.handonn.finapp.loans.service.impl;

import com.handonn.finapp.loans.entity.LoanEntity;
import com.handonn.finapp.loans.exception.ELoanErrorCode;
import com.handonn.finapp.loans.exception.LoanException;
import com.handonn.finapp.loans.model.LoanDto;
import com.handonn.finapp.loans.model.LoanUpdatedDto;
import com.handonn.finapp.loans.repository.LoanRepository;
import com.handonn.finapp.loans.service.ILoanService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LoanService implements ILoanService {

    private final LoanRepository loanRepository;

    public List<LoanDto> getAll() {
        return loanRepository.findAll().stream()
                .map(LoanDto::from)
                .collect(Collectors.toList());
    }

    public LoanDto getByLoanNumber(String loanNumber) {
        LoanEntity loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(
                () -> new LoanException.BusinessError(ELoanErrorCode.LOAN_NOT_FOUND)
        );

        return LoanDto.from(loan);
    }

    public void create(LoanDto loan) {
        LoanEntity loanEntity = new LoanEntity();
        boolean isExisted = loanRepository.existsByLoanNumber(loan.getLoanNumber());
        if (isExisted) {
            throw new LoanException.BusinessError(ELoanErrorCode.LOAN_DUPLICATION);
        }

        LoanDto.map(loanEntity, loan);
        loanRepository.save(loanEntity);
    }

    public void update(String loanNumber, LoanUpdatedDto loanUpdatedDto) {
        LoanEntity loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(
                () -> new LoanException.BusinessError(ELoanErrorCode.LOAN_NOT_FOUND)
        );

        LoanUpdatedDto.map(loan, loanUpdatedDto);

        loanRepository.save(loan);
    }

    @Transactional
    public void delete(String loanNumber) {
        LoanEntity loan = loanRepository.findByLoanNumber(loanNumber).orElseThrow(
                () -> new LoanException.BusinessError(ELoanErrorCode.LOAN_NOT_FOUND)
        );

        loanRepository.deleteById(loan.getId());
    }
}
