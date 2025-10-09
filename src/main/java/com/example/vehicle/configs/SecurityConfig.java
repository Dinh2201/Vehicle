package com.example.vehicle.configs;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class SecurityConfig {
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration)
            throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .cors(Customizer.withDefaults())
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(
                        auth ->
                                auth.requestMatchers("/actuator/health")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicles")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicle/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicle/update/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicle/delete")
                                        .permitAll()

//                                        Driver
                                        .requestMatchers("/api/v1/drivers")
                                        .permitAll()
                                        .requestMatchers("/api/v1/driver/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/driver/update/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/driver/delete")
                                        .permitAll()
                                        .requestMatchers("/api/v1/driver/*")
                                        .permitAll()

//                                        Vehicle Type
                                        .requestMatchers("/api/v1/vehicletypes")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicletype/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicletype/update/*")
                                        .permitAll()
                                        .requestMatchers("/api/v1/vehicletype/delete")
                                        .permitAll()

                                        .requestMatchers("/api/v1/history")
                                        .permitAll()

                                        .requestMatchers("/api/v1/booking_history")
                                        .permitAll()

                                        // Mock
                                        .requestMatchers("/api/v1/bookings/*/cancel").permitAll()
                                        .requestMatchers("/api/v2/driver/*")
                                        .permitAll()


                                        .anyRequest()
                                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // hoặc dùng List.of("http://localhost:8080")
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}
