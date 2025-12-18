package com.project.company_projects_management.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class EmployeeSecurityConfig {

    @Bean
    public InMemoryUserDetailsManager inMemoryUserDetailsManager() {

        UserDetails user = User.builder()
                .username("user")
                .password("{noop}test123")
                .roles("EMPLOYEE")
                .build();
        UserDetails manager = User.builder()
                .username("manager")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER")
                .build();
        UserDetails admin = User.builder()
                .username("admin")
                .password("{noop}test123")
                .roles("EMPLOYEE","MANAGER","ADMIN")
                .build();
        return new InMemoryUserDetailsManager(user, manager, admin);
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http.authorizeHttpRequests(configurer ->
                        configurer
                                .requestMatchers(HttpMethod.GET,"/api/employees").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.GET,"/api/employees/**").hasRole("EMPLOYEE")
                                .requestMatchers(HttpMethod.POST,"/api/employees").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PUT,"/api/employees/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.PATCH,"/api/employees/**").hasRole("MANAGER")
                                .requestMatchers(HttpMethod.DELETE,"/api/employees/**").hasRole("ADMIN")
                                .requestMatchers("/swagger-ui/**", "/v3/api-docs/**", "/swagger-ui.html").hasRole("ADMIN")
                                .anyRequest().authenticated()
        ).csrf(csrf -> csrf
                .ignoringRequestMatchers("/api/**","/h2-console/**")
        ).headers(headers -> headers.frameOptions(frame -> frame.disable())
        ).httpBasic(Customizer.withDefaults());

        return http.build();
    }
}
