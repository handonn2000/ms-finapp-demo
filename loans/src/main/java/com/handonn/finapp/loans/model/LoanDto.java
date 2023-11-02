package com.handonn.finapp.loans.model;

import com.handonn.finapp.loans.entity.ELoanType;
import com.handonn.finapp.loans.entity.LoanEntity;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LoanDto {

    private String mobileNumber;
    private String loanNumber;
    private ELoanType loanType;
    private BigDecimal totalLoan;
    private BigDecimal amountPaid;
    private BigDecimal outstandingAmount;

    public static LoanDto from(LoanEntity entity) {
        return LoanDto.builder()
                .mobileNumber(entity.getMobileNumber())
                .loanNumber(entity.getLoanNumber())
                .loanType(entity.getLoanType())
                .totalLoan(entity.getTotalLoan())
                .amountPaid(entity.getAmountPaid())
                .outstandingAmount(entity.getOutstandingAmount())
                .build();
    }

    public static void map(LoanEntity entity, LoanDto dto) {
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setLoanNumber(dto.getLoanNumber());
        entity.setLoanType(dto.getLoanType());
        entity.setTotalLoan(dto.getTotalLoan());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setOutstandingAmount(dto.getOutstandingAmount());
    }
}
