package com.hexaware.simplyfly.security;

/**
 * SecurityConfig configures Spring Security with JWT and role-based access.
 * Defines public endpoints, role-based API access, and stateless session.
 * @author Vikashini
 * @version 1.0
 */


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private CustomUserDetailsService userDetailsService;

    @Autowired
    private JwtRequestFilter jwtRequestFilter;

	

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) 

            .authorizeHttpRequests(auth -> auth
            	    // Public endpoints
            	    .requestMatchers(
            	        "/register",
            	        "/authenticate",
            	        "/v3/api-docs/**",
            	        "/swagger-ui/**",
            	        "/swagger-ui.html"
            	    ).permitAll()

            	    // /api/user - accessible by USER and ADMIN
            	    .requestMatchers("/api/user/**").hasAnyRole("USER", "ADMIN")

            	    // /api/flight - accessible by ADMIN and FLIGHT_OWNER
            	    .requestMatchers("/api/flights/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")

            	    // /api/booking - accessible by ADMIN and USER
            	    .requestMatchers("/api/bookings/**").hasAnyRole("ADMIN", "USER")

            	    // /api/route - accessible by ADMIN and FLIGHT_OWNER
            	    .requestMatchers("/api/routes/**").hasAnyRole("ADMIN", "FLIGHT_OWNER")

            	    // All other /api/** endpoints require authentication
            	    .requestMatchers("/api/**").authenticated()

            	    // Other requests must be authenticated
            	    .anyRequest().authenticated()
            	)
            .sessionManagement(session -> session
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
            );

        // Add JWT filter before username/password filter
        http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
