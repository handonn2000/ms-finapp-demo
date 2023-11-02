package com.handonn.finapp.accounts.entity;

import com.handonn.finapp.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity(name = "customers")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CustomerEntity extends BaseEntity {

    @Id
    @GeneratedValue(generator = "native", strategy = GenerationType.AUTO)
    private Long id;

    private String name;

    @Column(name = "email")
    private String email;

    @Column(name = "mobileNumber")
    private String mobileNumber;
}
