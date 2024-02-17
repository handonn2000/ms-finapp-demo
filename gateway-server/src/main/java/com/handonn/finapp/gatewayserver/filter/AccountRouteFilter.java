package com.handonn.finapp.gatewayserver.filter;

import org.springframework.cloud.gateway.route.builder.GatewayFilterSpec;
import org.springframework.cloud.gateway.route.builder.UriSpec;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class AccountRouteFilter {

    public UriSpec filter(GatewayFilterSpec gatewayFilterSpec) {
        return gatewayFilterSpec
                .rewritePath("/finapp/accounts/(?<segment>.*)", "/${segment}")
                .addResponseHeader("X-RESPONSE-TIME", LocalDateTime.now().toString());
    }
}
