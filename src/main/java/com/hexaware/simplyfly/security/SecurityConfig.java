package com.hexaware.simplyfly.security;

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

	/*
	 * @Bean public SecurityFilterChain securityFilterChain(HttpSecurity http)
	 * throws Exception { http.csrf(csrf -> csrf.disable())
	 * .authorizeHttpRequests(auth -> auth
	 * .requestMatchers(HttpMethod.POST,"/api/user").permitAll() .requestMatchers(
	 * "/api/users/register", "/api/users/login", "/v3/api-docs/**",
	 * "/swagger-ui/**", "/swagger-ui.html", "/register", "/authenticate"
	 * ).permitAll() .requestMatchers("/authenticate").permitAll()
	 * .requestMatchers("/api/users/**").hasAnyRole("ADMIN", "FLIGHT_OWNER","USER")
	 * .requestMatchers("/api/flights/**").hasRole("FLIGHT_OWNER")
	 * .requestMatchers("/api/bookings/**").hasRole("USER")
	 * .requestMatchers("/api/admin/**").hasRole("ADMIN")
	 * .anyRequest().authenticated() ) .sessionManagement(session -> session
	 * .sessionCreationPolicy(SessionCreationPolicy.STATELESS) );
	 * 
	 * http.addFilterBefore(jwtRequestFilter,
	 * UsernamePasswordAuthenticationFilter.class); return http.build(); }
	 */

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable()) // Disable CSRF for stateless APIs

				/*
				 * .authorizeHttpRequests(auth -> auth // Public endpoints - open to all
				 * .requestMatchers( "/register", "/authenticate", "/v3/api-docs/**",
				 * "/swagger-ui/**", "/swagger-ui.html" ).permitAll()
				 * 
				 * // ADMIN can create users and routes (POST) .requestMatchers(HttpMethod.POST,
				 * "/api/users").hasRole("ADMIN") .requestMatchers(HttpMethod.POST,
				 * "/api/routes").hasRole("ADMIN")
				 * 
				 * // FLIGHT_OWNER can manage flights (POST, PUT)
				 * .requestMatchers(HttpMethod.POST, "/api/flights").hasRole("FLIGHT_OWNER")
				 * .requestMatchers(HttpMethod.PUT, "/api/flights/**").hasRole("FLIGHT_OWNER")
				 * 
				 * // USER can manage bookings and view their own details
				 * .requestMatchers(HttpMethod.POST, "/api/bookings").hasRole("USER")
				 * .requestMatchers(HttpMethod.GET, "/api/users/**").hasRole("USER")
				 * .requestMatchers(HttpMethod.GET, "/api/bookings").hasRole("USER")
				 * .requestMatchers(HttpMethod.DELETE, "/api/bookings/**").hasRole("USER")
				 * 
				 * // Any other /api/** endpoints require authentication (can restrict further
				 * if needed) .requestMatchers("/api/**").authenticated()
				 * 
				 * // Any other requests must be authenticated .anyRequest().authenticated() )
				 */
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
