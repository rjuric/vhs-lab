package com.rjuric.vhs_lab.config;

import com.rjuric.vhs_lab.util.enums.Role;
import jakarta.servlet.Filter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(HttpMethod.GET, "/vhs/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.PUT, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/vhs/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.GET, "/rentals/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/rentals/**").hasAuthority(Role.USER.name())
                        .requestMatchers(HttpMethod.PUT, "/rentals/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.DELETE, "/rentals/**").hasAuthority(Role.ADMIN.name())
                        .requestMatchers(HttpMethod.POST, "/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/").permitAll()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

}

