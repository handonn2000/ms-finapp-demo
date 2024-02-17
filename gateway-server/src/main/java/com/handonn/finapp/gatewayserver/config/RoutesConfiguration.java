package com.handonn.finapp.gatewayserver.config;

import com.handonn.finapp.gatewayserver.filter.AccountRouteFilter;
import com.handonn.finapp.gatewayserver.filter.CardRouteFilter;
import com.handonn.finapp.gatewayserver.filter.LoanRouteFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.Buildable;
import org.springframework.cloud.gateway.route.builder.PredicateSpec;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RoutesConfiguration {

    private final AccountRouteFilter accountRouteFilter;
    private final CardRouteFilter cardRouteFilter;
    private final LoanRouteFilter loanRouteFilter;

    @Bean
    public RouteLocator routingLocator(RouteLocatorBuilder routeLocatorBuilder) {
        return routeLocatorBuilder.routes()
                .route("ACCOUNTS", this::accountRoute)
                .route("CARDS", this::cardRoute)
                .route("LOANS", this::loanRoute)
                .build();
    }

    @Bean
    public Buildable<Route> accountRoute(PredicateSpec ps) {
        return ps.path("/finapp/accounts/**")
                .filters(accountRouteFilter::filter)
                .uri("lb://ACCOUNTS");
    }

    @Bean
    public Buildable<Route> cardRoute(PredicateSpec ps) {
        return ps.path("/finapp/cards/**")
                .filters(cardRouteFilter::filter)
                .uri("lb://CARDS");
    }

    @Bean
    public Buildable<Route> loanRoute(PredicateSpec ps) {
        return ps.path("/finapp/loans/**")
                .filters(loanRouteFilter::filter)
                .uri("lb://LOANS");
    }
}
