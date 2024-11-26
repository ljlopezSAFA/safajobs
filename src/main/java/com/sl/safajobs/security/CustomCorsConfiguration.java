package com.sl.safajobs.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class CustomCorsConfiguration  {

        @Bean
        public CorsFilter corsFilter() {
            CorsConfiguration corsConfiguration = new CorsConfiguration();
            corsConfiguration.addAllowedOrigin("http://localhost:8101");
            corsConfiguration.addAllowedOrigin("http://10.65.11.166:8101");
            corsConfiguration.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, etc.)
            corsConfiguration.addAllowedHeader("*"); // Permitir todos los encabezados
            corsConfiguration.setAllowCredentials(true); // Si usas cookies o sesiones
            UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
            source.registerCorsConfiguration("/**", corsConfiguration);
            return new CorsFilter(source);
        }

}
