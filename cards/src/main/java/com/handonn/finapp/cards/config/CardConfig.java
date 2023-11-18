package com.handonn.finapp.cards.config;

import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
public record CardConfig(
        String serviceName,
        List<String> developers,
        Map<String, String> schemas) {
}
