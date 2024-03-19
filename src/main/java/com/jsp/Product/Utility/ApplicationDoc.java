package com.jsp.Product.utility;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
@OpenAPIDefinition
public class ApplicationDoc {

	Contact contact() {
		return new Contact().name("Vishv")
				.url("https://github.com/vishvXIX")
				.email("vishvpatoliya@gmail.com");
	}
	
	@Bean
	Info info () {
		return new Info().title("Product Management System")
				.description("RESTful API with basic CRUD operation.")
				.version("V1").contact(contact());
	}
	
	@Bean
	OpenAPI openAPI () {
		return new OpenAPI().info(info());
	}
	
	
	
}
