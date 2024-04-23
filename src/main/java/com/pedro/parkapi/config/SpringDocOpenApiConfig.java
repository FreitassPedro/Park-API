package com.pedro.parkapi.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .info(
                    new Info()
                            .title("REST API - Spring Park")
                            .description("API para gestão de estacionamento veiculos - Projeto estudos")
                            .version("v1")
                            .license(new License().name("Free to use by Udemy Course"))
                );
    }
}
