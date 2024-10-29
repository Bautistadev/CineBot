package com.spring.cinebot.Configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customUIOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("CineBot")
                        .version("1.0.0")
                        .description("Cinebot back end ")
                        .contact(new Contact()
                                .name("Bautista Basilio")
                                .email("BautistaBasilioDev@outlook.com")
                        )
                );
    }
}
