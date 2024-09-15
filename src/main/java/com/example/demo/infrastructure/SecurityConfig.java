package com.example.demo.infrastructure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public InMemoryUserDetailsManager userDetailsManager() {

        UserDetails john = User.builder()
                .username("john")
                .password("{noop}test123")
                .roles("Employee")
                .build();

        UserDetails mary = User.builder()
                .username("mary")
                .password("{noop}test123")
                .roles("Employee", "Manager")
                .build();

        UserDetails susan = User.builder()
                .username("susan")
                .password("{noop}test123")
                .roles("Employee", "Manager", "Admin")
                .build();

        return new InMemoryUserDetailsManager(john, mary, susan);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer -> {
            configurer
                    .requestMatchers(HttpMethod.GET, "/api/v2/employees/").hasRole("Employee")
                    .requestMatchers(HttpMethod.GET, "/api/v2/employees/**").hasRole("Employee")
                    .requestMatchers(HttpMethod.POST, "/api/v2/employees").hasRole("Manager")
                    .requestMatchers(HttpMethod.PATCH, "/api/v2/employees/**").hasRole("Manager")
                    .requestMatchers(HttpMethod.DELETE, "/api/v2/employees/**").hasRole("Admin");
        });

        // Use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)
        http.csrf(AbstractHttpConfigurer::disable);

        return http.build();
    }
}
