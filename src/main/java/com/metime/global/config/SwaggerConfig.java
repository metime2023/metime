package com.metime.global.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

	@Value("${server.title}")
	private String applicationTitle;
	@Value("${server.version}")
	private String applicationVersion;

	@Bean
	public OpenAPI openAPI() {
		return new OpenAPI()
			.info(new Info()
			.title(applicationTitle)
			.version(applicationVersion));
	}
}
