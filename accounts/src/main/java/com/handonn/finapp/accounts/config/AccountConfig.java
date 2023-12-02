package com.handonn.finapp.accounts.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "accounts")
@Getter @Setter
public class AccountConfig {
    private String serviceName;
    private List<String> developers;
    private Map<String, String> schemas;
}
