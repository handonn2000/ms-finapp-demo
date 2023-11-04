package com.handonn.finapp.loans.entity;

import com.handonn.finapp.common.entity.BaseEntity;
import jakarta.persistence.*;
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
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
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
