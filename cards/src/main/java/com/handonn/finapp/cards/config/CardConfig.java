package com.handonn.finapp.cards.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.List;
import java.util.Map;

@ConfigurationProperties(prefix = "cards")
@Getter @Setter
public class CardConfig {

    private String serviceName;
    private List<String> developers;
    private Map<String, String> schemas;
}
