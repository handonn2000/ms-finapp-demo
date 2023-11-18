package com.handonn.finapp.accounts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@ConfigurationPropertiesScan(value = "com.handonn.finapp.accounts.config")
@SpringBootApplication(scanBasePackages = {
        "com.handonn.finapp.accounts",
        "com.handonn.finapp.common"
})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
