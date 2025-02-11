package com.sl.safajobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

import java.util.Arrays;


@Configuration
public class CustomCorsConfiguration  {

        @Bean
        public CorsFilter corsFilter() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.setAllowedOrigins(Arrays.asList("http://localhost:4200", "https://safajobsapp.onrender.com"));
            corsConfiguration.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
            corsConfiguration.addAllowedHeader("*"); // Permitir todos los encabezados
            corsConfiguration.setAllowCredentials(true); // Si usas cookies o sesiones
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsFilter(source);
        }

}
