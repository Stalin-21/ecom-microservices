package com.ecommerce.gateway;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
public class GatwayConfig {

//    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder){
        return builder.routes()
                .route("product-service",r -> r
                        .path("/products/**")
                        .filters(f -> f.rewritePath("/products/?(?<segment>.*)",
                                "/api/products${segment}"))
                        .uri("lb://PRODUCT-SERVICE"))

                .route("user-service",r -> r
                        .path("/users/**")
                        .filters(f -> f.rewritePath("/users/?(?<segment>.*)",
                                "/api/users${segment}"))
                        .uri("lb://USER-SERVICE"))

                .route("order-service", r -> r
                        .path("/orders/**", "/cart/**")
                        .filters(f -> f.rewritePath(
                                "/(?<segment>orders|cart)(?<remaining>/?.*)",
                                "/api/${segment}${remaining}"
                        ))
                        .uri("lb://ORDER-SERVICE"))
                .route("eureka-service",r -> r
                        .path("/eureka/main")
                        .filters(f -> f.setPath("/"))
                        .uri("http://localhost:8761"))
                .route("eureka-static-resources",r -> r
                        .path("/eureka/**")
                        .uri("http://localhost:8761"))
                .build();
    }
}
