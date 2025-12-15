package com.shoplite.api_gateway.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.function.*;

import static org.springframework.cloud.gateway.server.mvc.handler.GatewayRouterFunctions.route;
import static org.springframework.cloud.gateway.server.mvc.handler.HandlerFunctions.http;

@Configuration
@Slf4j
public class ApiGatewayConfig {

    @Bean
    public RouterFunction<?> routes() {

        RouterFunction<ServerResponse> productRoutes =
                route("product-service")
                        .GET("/api/v1/products/**",http("http://localhost:8082"))
                        .filter(requestLoggingFilter())   // ⬅️ ici .filter() et plus .before()
                        .build();

        RouterFunction<ServerResponse> orderRoutes =
                route("order-service")
                        .GET("/api/v1/orders/**",http("http://localhost:8083"))
                        .filter(requestLoggingFilter())   // ⬅️ pareil
                        .build();

        RouterFunction<ServerResponse> customerRoutes =
                route("customer-service")
                        .GET("/api/v1/customers/**",http("http://localhost:8084"))
                        .filter(requestLoggingFilter())   // ⬅️ pareil
                        .build();



        // ✅ On combine les routes
        return productRoutes.andOther(orderRoutes);

    }

    @Bean
    public HandlerFilterFunction<ServerResponse, ServerResponse> requestLoggingFilter() {
        return (request, next) -> {
            log.info("Incoming request: {} {}", request.method(), request.path());
            ServerResponse response = next.handle(request);
            log.info("Outgoing response: {}", response.statusCode());
            return response;
        };
    }

}
