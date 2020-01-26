package com.tokigames.config;

import java.time.LocalDate;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;

import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
@Configuration
public class AppConfig {

    @Bean
    @Profile("swagger")
    public Docket connectorApi() {
        return new Docket(DocumentationType.SWAGGER_2).apiInfo(new ApiInfo(
                "pmo",
                "pmo"
                , "1`.0", null, "pmo", null, null))
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.ge.pw.pmo.rest.controller"))
                .paths(PathSelectors.any())
                .build()
                .pathMapping("/")
                .directModelSubstitute(LocalDate.class,
                        String.class)
                .genericModelSubstitutes(ResponseEntity.class)
                .useDefaultResponseMessages(false);
    }
}