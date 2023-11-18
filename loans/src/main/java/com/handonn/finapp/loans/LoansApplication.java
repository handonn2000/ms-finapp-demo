package com.handonn.finapp.loans;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication(scanBasePackages = {"com.handonn.finapp.loans", "com.handonn.finapp.common"})
@ConfigurationPropertiesScan(value = "com.handonn.finapp.loans.config")
public class LoansApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoansApplication.class, args);
	}

}
