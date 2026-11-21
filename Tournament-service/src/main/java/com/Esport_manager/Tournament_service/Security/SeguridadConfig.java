package com.Esport_manager.Tournament_service.Security;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SeguridadConfig {

    @Value("${app.cors.origenes}")
    private String[] origenes;

    @Bean
    SecurityFilterChain filtros(HttpSecurity http, JwtFiltro jwtFiltro) throws Exception {
        http.exceptionHandling(e -> e
                .authenticationEntryPoint((req, res, ex) -> responder(res, 401, "Sesión no válida o vencida"))
                .accessDeniedHandler((req, res, ex) -> responder(res, 403, "Sin permiso para esta acción")));

        return http
                .cors(Customizer.withDefaults())
                .csrf(csrf -> csrf.disable())
                .sessionManagement(s -> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/error", "/actuator/health",
                                "/swagger-ui/**", "/swagger-ui.html", "/v3/api-docs/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/v1/torneos/**").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/v1/torneos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT, "/api/v1/torneos/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE, "/api/v1/torneos/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtFiltro, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(Arrays.asList(origenes));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    private static void responder(HttpServletResponse res, int estado, String mensaje) throws IOException {
        res.setStatus(estado);
        res.setContentType("application/json; charset=UTF-8");
        res.getWriter().write("{\"mensaje\": \"" + mensaje + "\"}");
    }
}