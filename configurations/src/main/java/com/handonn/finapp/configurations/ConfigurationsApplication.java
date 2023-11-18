package com.handonn.finapp.configurations;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

@EnableConfigServer
@SpringBootApplication
public class ConfigurationsApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConfigurationsApplication.class, args);
	}

}
