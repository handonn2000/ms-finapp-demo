package com.handonn.finapp.accounts.controller;

import com.handonn.finapp.accounts.config.AccountConfig;
import com.handonn.finapp.common.model.BaseResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/build")
@RequiredArgsConstructor
@Tag(name = "Actuator API", description = "Monitoring Service Info")
@Validated
public class ActuatorController {


    private final Environment environment;

    private final AccountConfig accountConfig;

    @GetMapping("/version")
    public BaseResponse<String> getBuildVersion() {
        return BaseResponse.<String>builder()
                .statusCode(HttpStatus.OK)
                .message("build version")
                .data(environment.getProperty("build.version"))
                .build();
    }

    @GetMapping("/info")
    public BaseResponse<AccountConfig> getAccountServiceInfo() {
        return BaseResponse.<AccountConfig>builder()
                .statusCode(HttpStatus.OK)
                .message("account config info")
                .data(accountConfig)
                .build();
    }
}
