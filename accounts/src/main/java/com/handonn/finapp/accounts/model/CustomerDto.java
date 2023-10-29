package com.handonn.finapp.accounts.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.handonn.finapp.accounts.entity.CustomerEntity;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CustomerDto {

    @NotEmpty(message = "name is not allow empty")
    @Size(max = 10, message = "name length is not allow over 10 characters")
    private String name;

    @NotEmpty(message = "email is not allow empty")
    @Email(message = "invalid email")
    private String email;

    @NotEmpty(message = "phone is not allow empty")
    @Pattern(regexp = "(^$|[0-9]{10})", message = "invalid mobile number")
    private String mobileNumber;

    @JsonIgnoreProperties(value = {"customerId", "accountNumber"})
    @Valid
    private AccountDto accountInfo;

    public static CustomerDto from(CustomerEntity entity) {
        return CustomerDto.builder()
                .email(entity.getEmail())
                .mobileNumber(entity.getMobileNumber())
                .name(entity.getName())
                .build();
    }

    public static void map(CustomerEntity entity, CustomerDto customerDto) {
        entity.setEmail(customerDto.getEmail());
        entity.setMobileNumber(customerDto.getMobileNumber());
        entity.setName(customerDto.getName());
    }
}
