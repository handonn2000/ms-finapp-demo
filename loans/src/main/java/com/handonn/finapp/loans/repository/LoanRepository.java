package com.handonn.finapp.loans.repository;

import com.handonn.finapp.loans.entity.LoanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoanRepository extends JpaRepository<LoanEntity, Long> {

    boolean existsByLoanNumber(String loanNumber);

    Optional<LoanEntity> findByLoanNumber(String loanNumber);
}
