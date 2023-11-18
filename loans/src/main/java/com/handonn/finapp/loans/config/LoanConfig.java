package com.handonn.finapp.loans.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
public record LoanConfig(
        String serviceName,
        List<String> developers,
        Map<String, String> schemas) {
}
