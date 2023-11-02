package com.handonn.finapp.loans.repository;

import com.handonn.finapp.loans.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsByLoanNumber(String loanNumber);
}
