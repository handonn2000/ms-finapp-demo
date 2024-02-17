package com.handonn.finapp.gatewayserver.filter;

import com.handonn.finapp.gatewayserver.utils.FilterUtility;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@Order(1)
@RequiredArgsConstructor
public class RequestTraceFilter implements GlobalFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(RequestTraceFilter.class);

    private final FilterUtility filterUtility;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        HttpHeaders requestHeaders = exchange.getRequest().getHeaders();
        String correlationId = filterUtility.getCorrelationId(requestHeaders);

        boolean isCorrelationIdExist = StringUtils.isNotEmpty(correlationId);
        if (isCorrelationIdExist) {
            LOGGER.debug("[RequestTraceFilter] CorrelationId: {}", correlationId);
        } else {
            correlationId = filterUtility.generateCorrelationId();
            exchange = filterUtility.addCorrelationRequestHeader(exchange, correlationId);
            LOGGER.debug("[RequestTraceFilter] CorrelationId generated: {}", correlationId);
        }

        return chain.filter(exchange);
    }
}
