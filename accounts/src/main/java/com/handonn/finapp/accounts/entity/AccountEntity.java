package com.handonn.finapp.accounts.entity;

import com.handonn.finapp.accounts.model.EAccountType;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "accounts")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class AccountEntity extends BaseEntity {

    @Id
    private String accountNumber;

    @Column(updatable = false)
    private Long customerId;

    @Enumerated(EnumType.STRING)
    private EAccountType accountType;

    private String branchAddress;
}
