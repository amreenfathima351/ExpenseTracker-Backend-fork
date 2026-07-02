package com.expensetracker.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI expenseTrackerOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Expense Tracker API")
                        .version("v1")
                        .description("Spring Boot REST API for managing expenses and categories")
                        .contact(new Contact().name("Expense Tracker Team")));
    }
}