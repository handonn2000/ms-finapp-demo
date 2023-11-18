package com.handonn.finapp.accounts.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
public record AccountConfig(
        String serviceName,
        List<String> developers,
        Map<String, String> schemas) {
}
