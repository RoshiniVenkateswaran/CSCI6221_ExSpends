package com.example.expensetracker.config

import org.springframework.context.annotation.Bean
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.crypto.password.NoOpPasswordEncoder
import org.springframework.security.web.SecurityFilterChain
import org.springframework.context.annotation.Configuration

@Configuration
class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable()
            .authorizeHttpRequests()
            .requestMatchers("/api/auth/**").permitAll() // Allow unauthenticated access to auth endpoints
            .anyRequest().permitAll()
            .and()
            .httpBasic() // Use basic auth for other endpoints
        return http.build()
    }

    @Bean
    AuthenticationManager authManager(HttpSecurity http) throws Exception {
        return http.getSharedObject(AuthenticationManagerBuilder.class).build()
    }

    @Bean
    NoOpPasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance() // No password encryption for simplicity
    }
}
