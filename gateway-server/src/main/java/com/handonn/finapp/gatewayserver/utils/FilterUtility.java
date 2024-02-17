package com.handonn.finapp.gatewayserver.utils;

import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.UUID;

@Component
public class FilterUtility {
    private static final String CORRELATION_ID = "finapp-correlation-id";

    public String generateCorrelationId() {
        return UUID.randomUUID().toString();
    }

    public String getCorrelationId(HttpHeaders headers) {
        return headers.getOrEmpty(CORRELATION_ID)
                .stream().findFirst()
                .orElse(StringUtils.EMPTY);
    }

    public ServerWebExchange addRequestHeader(ServerWebExchange serverWebExchange, String name, String value) {
        ServerHttpRequest requestWithHeader = serverWebExchange.getRequest().mutate()
                .header(name, value)
                .build();

        return serverWebExchange.mutate()
                .request(requestWithHeader)
                .build();
    }

    public ServerWebExchange addCorrelationRequestHeader(ServerWebExchange serverWebExchange, String correlationId) {
        return this.addRequestHeader(serverWebExchange, CORRELATION_ID, correlationId);
    }

    public void addResponseHeader(ServerWebExchange serverWebExchange, String name, String value) {
        serverWebExchange.getResponse().getHeaders().add(name, value);
    }

    public void addCorrelationResponseHeader(ServerWebExchange serverWebExchange, HttpHeaders requestHeaders) {
        String correlationId = this.getCorrelationId(requestHeaders);
        this.addResponseHeader(serverWebExchange, CORRELATION_ID, correlationId);
    }
}
