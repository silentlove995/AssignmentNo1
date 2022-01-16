package com.microservice.gateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableHystrix
public class GatewayConfig {
    @Autowired
    private AuthenticationFilter filter;

    /**
     * routes
     *
     * @param builder
     * @return
     */
    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
                .route("customer", r -> r.path("/customer/**", "/admin/customer/**", "/public/customer/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://customer"))
                .route("auth-service", r -> r.path("/auth/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://auth-service"))
                .route("users", r -> r.path("/user/**", "/admin/user/**", "/public/user/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://users"))
                .route("products", r -> r.path("/product/**", "/admin/product/**", "/public/product/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://products"))
                .route("orders", r -> r.path("/order/**", "/admin/order/**", "/public/order/**")
                        .filters(f -> f.filter(filter))
                        .uri("lb://orders"))
                .build();
    }
}
