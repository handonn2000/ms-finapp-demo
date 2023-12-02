package com.handonn.finapp.cards;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.cloud.context.config.annotation.RefreshScope;

@SpringBootApplication(scanBasePackages = {"com.handonn.finapp.cards", "com.handonn.finapp.common"})
@ConfigurationPropertiesScan(value = "com.handonn.finapp.cards.config")
@RefreshScope
public class CardsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CardsApplication.class, args);
    }

}
