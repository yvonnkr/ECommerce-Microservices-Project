package com.yvolabs.gateway.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

/**
 * @author Yvonne N
 * @version 1.0
 * @since 26/07/2024
 * @apiNote <code>
 *     api-gateway dependency (spring-cloud-starter-gateway) is reactive web, it includes dependency (spring-boot-starter-webflux)
 *     hence instead of enabling spring security, we use @EnableWebFluxSecurity
 * </code>
 */

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authorizeExchange(exchange -> exchange
                        .pathMatchers("/eureka/**", "/api/v1/customers/**").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/v1/products/purchase").permitAll()
                        .pathMatchers(HttpMethod.POST, "/api/v1/payments/**").permitAll()
                        .anyExchange()
                        .authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));

        return serverHttpSecurity.build();

    }
}
