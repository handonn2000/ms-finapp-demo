package com.handonn.finapp.loans.model;

import com.handonn.finapp.common.validation.EnumValidator;
import com.handonn.finapp.loans.entity.ELoanType;
import com.handonn.finapp.loans.entity.LoanEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LoanDto {

    @Pattern(regexp = "^FIN_\\d{10}$", message = "Invalid loan number")
    private String loanNumber;

    @Pattern(regexp = "(^$|[0-9]{10})", message = "invalid mobile number")
    private String mobileNumber;

    @EnumValidator(enumClass = ELoanType.class)
    private String loanType;

    @Min(value = 1000, message = "TotalLoan must be equal or greater than 1000")
    private BigDecimal totalLoan;

    @Min(value = 1000, message = "AmountPaid must be at least equal or greater than 1000")
    private BigDecimal amountPaid;

    @Min(value = 1000, message = "OutstandingAmount must be equal or greater than 1000")
    private BigDecimal outstandingAmount;

    public static LoanDto from(LoanEntity entity) {
        return LoanDto.builder()
                .mobileNumber(entity.getMobileNumber())
                .loanNumber(entity.getLoanNumber())
                .loanType(entity.getLoanType().name())
                .totalLoan(entity.getTotalLoan())
                .amountPaid(entity.getAmountPaid())
                .outstandingAmount(entity.getOutstandingAmount())
                .build();
    }

    public static void map(LoanEntity entity, LoanDto dto) {
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setLoanNumber(dto.getLoanNumber());
        entity.setLoanType(ELoanType.valueOf(dto.getLoanType()));
        entity.setTotalLoan(dto.getTotalLoan());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setOutstandingAmount(dto.getOutstandingAmount());
    }
}
