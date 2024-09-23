package io.egargo.spring_docker.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableWebMvc
public class CorsConfig implements WebMvcConfigurer {
	@Value("${security.cors.allowed-origin}")
	private String allowedOrigin;

	@Value("${security.cors.allow-credentials}")
	private boolean allowCredentials;

	@Value("${security.cors.max-age}")
	private int maxAge;

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry
				.addMapping("/api/**")
				.allowedMethods("GET", "POST", "PUT", "PATCH", "DELETE")
				.allowedOrigins(allowedOrigin)
				.allowCredentials(allowCredentials)
				.maxAge(maxAge);
	}
}
