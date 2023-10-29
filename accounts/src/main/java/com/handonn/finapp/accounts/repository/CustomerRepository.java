package com.handonn.finapp.accounts.repository;

import com.handonn.finapp.accounts.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity, Long> {

    Optional<CustomerEntity> findByCustomerId(Long id);
    Optional<CustomerEntity> findByMobileNumber(String phone);

    boolean existsByMobileNumber(String phone);
    boolean existsByEmail(String email);
}
