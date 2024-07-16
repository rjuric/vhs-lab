package com.rjuric.vhs_lab.config;

import com.rjuric.vhs_lab.util.enums.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.POST, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/rentals/**").hasAuthority(Role.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/rentals/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/rentals/**").hasAuthority(Role.ADMIN.name())
                        .anyRequest().permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }
}

