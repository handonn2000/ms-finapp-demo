package com.handonn.finapp.loans.entity;


import com.handonn.finapp.common.entity.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity(name = "loans")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoanEntity extends BaseEntity {

    @Id
    private Long id;

    @NotEmpty
    private String mobileNumber;

    @NotEmpty
    private String loanNumber;

    @NotNull
    @Enumerated(EnumType.STRING)
    private ELoanType loanType;

    @NotNull
    private BigDecimal totalLoan;

    @NotNull
    private BigDecimal amountPaid;

    @NotNull
    private BigDecimal outstandingAmount;
}
