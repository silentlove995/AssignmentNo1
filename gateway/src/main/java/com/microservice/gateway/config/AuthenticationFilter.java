package com.microservice.gateway.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microservice.gateway.constants.AppConstant;
import com.microservice.gateway.dto.VerifyPathBuilder;
import com.microservice.gateway.reponse.ResponseError;
import io.jsonwebtoken.Claims;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@RefreshScope
@Component
@Slf4j
public class AuthenticationFilter {
    private static final String AUTHORIZATION_MISSING = "Authorization header is missing in request";
    private static final String EXPIRED_DATE = "expired token";
    private static final String UNAUTHORIZED = "Unauthorized path";

    @Autowired
    private RouterValidator routerValidator;
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private VerifyPathBuilder verifyPathBuilder;

    @SneakyThrows
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        ServerHttpRequest request = exchange.getRequest();

        /*discoveryClient.getInstances("customer").forEach((ServiceInstance s) -> {
            log.info(ToStringBuilder.reflectionToString(s));
        });*/

        if (routerValidator.isSecured.test(request)) {
            if (this.isAuthMissing(request))
                return this.onError(exchange, new ResponseError(HttpStatus.UNAUTHORIZED.toString(), AUTHORIZATION_MISSING), HttpStatus.UNAUTHORIZED);

            final String token = this.getAuthHeader(request).substring(AppConstant.O2Constants.TOKEN_PREFIX.length());

            if (jwtUtil.isInvalid(token))
                return this.onError(exchange, new ResponseError(HttpStatus.UNAUTHORIZED.toString(), EXPIRED_DATE), HttpStatus.UNAUTHORIZED);

            Claims claims = jwtUtil.getAllClaimsFromToken(token);
            /*claims.get("roles").toString().replace("/[^a-zA-Z0-9]/g", "").split(",")*/
            boolean isPathAndRole = verifyPathBuilder
                    .setPath(exchange.getRequest().getPath().toString())
                    .setRoles(claims.get("roles").toString().replace("[", "").replace("]", "").split(","))
                    .build();

            if (!isPathAndRole) {
                return this.onError(exchange, new ResponseError(HttpStatus.UNAUTHORIZED.toString(), UNAUTHORIZED), HttpStatus.UNAUTHORIZED);
            }
            this.populateRequestWithHeaders(exchange, claims);
        }
        return chain.filter(exchange);
    }


    /*PRIVATE*/

    private Mono<Void> onError(ServerWebExchange exchange, ResponseError err, HttpStatus httpStatus) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        ServerHttpResponse response = exchange.getResponse();
        response.setStatusCode(httpStatus);
        response.getHeaders().setContentType(MediaType.APPLICATION_JSON);
        DataBuffer dataBuffer = response.bufferFactory().wrap(objectMapper.writeValueAsBytes(err));
        return response.writeWith(Mono.just(dataBuffer));
    }

    private String getAuthHeader(ServerHttpRequest request) {
        return request.getHeaders().getOrEmpty("Authorization").get(0);
    }

    private boolean isAuthMissing(ServerHttpRequest request) {
        return !request.getHeaders().containsKey("Authorization");
    }

    private void populateRequestWithHeaders(ServerWebExchange exchange, Claims claims) {
        exchange.getRequest().mutate()
                .header("id", String.valueOf(claims.get("id")))
                .header("username", String.valueOf(claims.get("username")))
                .header("roles", String.valueOf(claims.get("roles")))
                .build();
    }
}
