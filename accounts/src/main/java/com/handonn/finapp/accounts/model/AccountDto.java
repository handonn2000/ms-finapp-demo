package com.handonn.finapp.accounts.model;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.handonn.finapp.accounts.entity.AccountEntity;
import com.handonn.finapp.accounts.validation.EnumValidator;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class AccountDto {

    private Long customerId;

    private String accountNumber;

    @EnumValidator(enumClass = EAccountType.class, message = "Account Type is not match")
    private String accountType;

    @Size(max = 100, message = "address is too long, 100 characters is allowed")
    private String branchAddress;

    public static AccountDto from(AccountEntity entity) {
        return AccountDto.builder()
                .accountNumber(entity.getAccountNumber())
                .accountType(entity.getAccountType().name())
                .branchAddress(entity.getBranchAddress())
                .build();
    }

    public static void map(AccountEntity entity, AccountDto dto) {
        entity.setAccountType(EAccountType.valueOf(dto.getAccountType()));
        entity.setBranchAddress(dto.getBranchAddress());
    }
}
