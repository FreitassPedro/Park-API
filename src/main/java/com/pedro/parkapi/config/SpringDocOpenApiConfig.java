package com.pedro.parkapi.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.info.License;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SpringDocOpenApiConfig {
    @Bean
    public OpenAPI openAPI() {
        return new OpenAPI()
                .components(
                        new Components().addSecuritySchemes("security", securityScheme()))
                .info(
                    new Info()
                            .title("REST API - Spring Park")
                            .description("API para gestão de estacionamento veiculos - Projeto estudos")
                            .version("v1")
                            .license(new License().name("Free to use by Udemy Course"))
                );
    }

    private SecurityScheme securityScheme() {
        return new SecurityScheme()
                .description("Insira um bearer token válido para prosseguir")
                .type(SecurityScheme.Type.HTTP)
                .in(SecurityScheme.In.HEADER)
                .scheme("bearer")
                .bearerFormat("JWT")
                .name("security");
    }
}
