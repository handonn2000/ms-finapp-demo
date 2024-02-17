package com.handonn.finapp.gatewayserver.filter;

import com.handonn.finapp.gatewayserver.utils.FilterUtility;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import reactor.core.publisher.Mono;

@Configuration
@RequiredArgsConstructor
public class ResponseTraceFilter {
    private static final Logger LOGGER = LoggerFactory.getLogger(ResponseTraceFilter.class);

    private final FilterUtility filterUtility;

    @Bean
    public GlobalFilter postGlobalFilter() {
        return (exchange, chain) -> {
            HttpHeaders requestHeaders = exchange.getRequest().getHeaders();

            return chain.filter(exchange).then(Mono.fromRunnable(() -> {
                filterUtility.addCorrelationResponseHeader(exchange, requestHeaders);
            }));
        };
    }



}
