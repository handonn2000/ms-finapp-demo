package com.handonn.finapp.loans.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "loans")
@Getter
@Setter
public class LoanConfig {

    private String serviceName;
    private String envName;
    private List<String> developers;
    private Map<String, String> schemas;
}
