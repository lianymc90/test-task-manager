package com.example.taskmanager;

import java.util.Collections;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration

public class SwaggerConfig {


    /**
     * Esta clase se construye el contrato Swagger de la Api incorporando alguna informacion propia de la Api
     * para esto utiliza la dependencia
     * <dependency>
     * <groupId>org.springdoc</groupId>
     * <artifactId>springdoc-openapi-ui</artifactId>
     * <version>1.6.7</version>
     * </dependency>
     **/
    @Bean
    public OpenAPI customOpenAPI(@Value("${application-description}") String appDescription, @Value("${application-version}") String appVersion
            , @Value("${application-name}") String appName, @Value("${application-business-web}") String appBusinessWeb) {
        return new OpenAPI()
                .info(new Info()
                        .title(appName)
                        .version(appVersion)
                        .description(appDescription)
                        .contact(new Contact().name(appBusinessWeb).email(appBusinessWeb)));
    }
}