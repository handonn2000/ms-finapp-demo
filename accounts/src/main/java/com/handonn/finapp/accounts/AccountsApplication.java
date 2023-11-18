package com.handonn.finapp.accounts;

import com.handonn.finapp.accounts.config.AccountConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication(scanBasePackages = {"com.handonn.finapp.accounts", "com.handonn.finapp.common"})
@EnableConfigurationProperties(value = {AccountConfig.class})
public class AccountsApplication {

    public static void main(String[] args) {
        SpringApplication.run(AccountsApplication.class, args);
    }
}
