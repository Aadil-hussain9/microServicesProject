package com.infuse.app;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Configuration
public class CustomFilter implements GlobalFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        Logger logger = LoggerFactory.getLogger(CustomFilter.class);
        ServerHttpRequest request = exchange.getRequest();

        logger.info("Authorization " + request.getHeaders().getFirst("Authorization"));

        return chain.filter(exchange).then(Mono.fromRunnable(() ->
        {

            ServerHttpResponse response = exchange.getResponse();

            /* Response from particular service */
            if (request.getURI().toString().contains("/api/student/")) {
                logger.info("Post filter = " + response.getStatusCode());
                logger.info("Headers  " + response.getHeaders());
            }
            /* for all responses from server
          logger.info("Post filter = "+ response.getStatusCode() );
          logger.info("Headers  " +response.getHeaders());*/
        }));
    }
}
