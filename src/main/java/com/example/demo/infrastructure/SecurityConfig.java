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
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import javax.sql.DataSource;

@Configuration
public class SecurityConfig {

//    In-Memory authentication
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//
//        UserDetails john = User.builder()
//                .username("john")
//                .password("{noop}test123")
//                .roles("Employee")
//                .build();
//
//        UserDetails mary = User.builder()
//                .username("mary")
//                .password("{noop}test123")
//                .roles("Employee", "Manager")
//                .build();
//
//        UserDetails susan = User.builder()
//                .username("susan")
//                .password("{noop}test123")
//                .roles("Employee", "Manager", "Admin")
//                .build();
//
//        return new InMemoryUserDetailsManager(john, mary, susan);
//    }

//    JDBC Authentication implemented.
//    @Bean
//    public UserDetailsManager userDetailsManager(DataSource dataSource) {
//        return new JdbcUserDetailsManager(dataSource);
//    }

    //    JDBC Authentication implemented with custom tables.
    @Bean
    public UserDetailsManager userDetailsManager(DataSource dataSource) {
        var jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);

        jdbcUserDetailsManager.setUsersByUsernameQuery("select user_id, pw, active from members where user_id=?");

        jdbcUserDetailsManager.setAuthoritiesByUsernameQuery("select user_id, role from roles where user_id=?");

        return jdbcUserDetailsManager;
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

            configurer.anyRequest().permitAll(); // Allow access to any other route not specified in this file.
        });

        // Use HTTP Basic Authentication
        http.httpBasic(Customizer.withDefaults());

        // Disable Cross Site Request Forgery (CSRF)
        http.csrf(csrfConfigurer -> {
            csrfConfigurer.ignoringRequestMatchers("/api/**");
        });

        return http.build();
    }
}
