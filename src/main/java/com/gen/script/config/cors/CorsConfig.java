package com.gen.script.config.cors;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class CorsConfig{
	
	@Value("${cors.allowedOrigins}")
	private String allowedOrigins;
	
	@Value("${cors.allowedMethods}")
	private String allowedMethods;
	
	@Value("${cors.allowedHeaders}")
	private String allowedHeaders;
	
	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		UrlBasedCorsConfigurationSource corsConfigurationSource = new UrlBasedCorsConfigurationSource();
		corsConfigurationSource.registerCorsConfiguration("/**", corsConfiguration());
		return corsConfigurationSource;
	}
	
	@Bean
	CorsConfiguration corsConfiguration() {
		List<String> origins = allowedOrigins.isBlank()? List.of("*"): List.of(allowedOrigins.split(","));
		List<String> methods = allowedMethods.isBlank()? List.of("*"): List.of(allowedMethods.split(","));
		List<String> headers = allowedHeaders.isBlank()? List.of("*"): List.of(allowedHeaders.split(","));
		
		CorsConfiguration config = new CorsConfiguration();
		config.setAllowedOrigins(origins);
		config.setAllowedMethods(methods);
		config.setAllowedHeaders(headers);
		config.setAllowCredentials(true);		//쿠키, 인증헤더를 포함할 수 있는지 여부를 결정합니다.
		config.setMaxAge(3600L);				//리퀘스트 캐싱
		return config;
	}
}