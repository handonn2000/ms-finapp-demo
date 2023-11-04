package com.handonn.finapp.loans.model;

import com.handonn.finapp.loans.entity.LoanEntity;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;

@Getter
@Builder
public class LoanUpdatedDto {

    @Pattern(regexp = "(^$|[0-9]{10})", message = "invalid mobile number")
    private String mobileNumber;

    @Min(value = 1000, message = "TotalLoan must be equal or greater than 1000")
    private BigDecimal totalLoan;

    @Min(value = 1000, message = "AmountPaid must be at least equal or greater than 1000")
    private BigDecimal amountPaid;

    @Min(value = 1000, message = "OutstandingAmount must be equal or greater than 1000")
    private BigDecimal outstandingAmount;

    public static LoanUpdatedDto from(LoanEntity entity) {
        return LoanUpdatedDto.builder()
                .mobileNumber(entity.getMobileNumber())
                .totalLoan(entity.getTotalLoan())
                .amountPaid(entity.getAmountPaid())
                .outstandingAmount(entity.getOutstandingAmount())
                .build();
    }

    public static void map(LoanEntity entity, LoanUpdatedDto dto) {
        entity.setMobileNumber(dto.getMobileNumber());
        entity.setTotalLoan(dto.getTotalLoan());
        entity.setAmountPaid(dto.getAmountPaid());
        entity.setOutstandingAmount(dto.getOutstandingAmount());
    }
}
