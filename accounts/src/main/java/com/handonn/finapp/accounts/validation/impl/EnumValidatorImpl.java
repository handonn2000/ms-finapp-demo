package com.handonn.finapp.accounts.validation.impl;

import com.handonn.finapp.accounts.validation.EnumValidator;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class EnumValidatorImpl implements ConstraintValidator<EnumValidator, String> {

    List<String> valueList = null;

    @Override
    public void initialize(EnumValidator constraintAnnotation) {
        Enum<?>[] enums = constraintAnnotation.enumClass().getEnumConstants();
        valueList = Arrays.stream(enums).map(Enum::name).collect(Collectors.toList());
    }

    @Override
    public boolean isValid(String enumValue, ConstraintValidatorContext constraintValidatorContext) {
        return enumValue != null && valueList.contains(enumValue.toUpperCase());
    }
}
