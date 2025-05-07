package com.erikv121.blogapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    private final String successUrl = "http://localhost:8080/blog/home";
    private final String logoutUrl = "http://localhost:8081/realms/Blogified-Realm/protocol/openid-connect/logout";
    private final String redirectUri = "http://localhost:8080/oauth2/authorization/keycloak";

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        TODO need to fix the logout url
        http
                .csrf(AbstractHttpConfigurer::disable)
                .oauth2Login(oauth2Login -> oauth2Login
                        .successHandler(new SimpleUrlAuthenticationSuccessHandler(successUrl))
                )
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.ALWAYS)
                )
                .authorizeHttpRequests(authz -> authz
                        .requestMatchers("/unauthenticated", "/oauth2/**", "/login/**").permitAll()
                        .anyRequest().authenticated()
                )
                .logout(logout -> logout
                        .logoutSuccessUrl(logoutUrl + "?redirect_uri=" + redirectUri)
                );
 //                .oauth2ResourceServer(auth ->
//                        auth.jwt(token ->
//                                token.jwtAuthenticationConverter(new KeycloakJwtAuthenticationConverter()))
//                );
        return http.build();
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        //for testing only
        return NoOpPasswordEncoder.getInstance();
    }
}
