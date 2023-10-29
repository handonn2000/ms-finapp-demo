package com.handonn.finapp.accounts.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

@Entity(name = "customers")
@Getter @Setter @Builder
@AllArgsConstructor @NoArgsConstructor
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
    @GenericGenerator(name = "native")
    private Long customerId;

    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobileNumber")
    private String mobileNumber;
}
