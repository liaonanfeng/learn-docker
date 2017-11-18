package com.learn.it.conf;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableWebMvc
@EnableSwagger2
public class SwaggerConfig {
    @Bean
    public Docket buildDocket() {
        return new Docket(DocumentationType.SWAGGER_2)
        .apiInfo(buildApiInf())
        .host("localhost:8010")
        .select()
        .apis(RequestHandlerSelectors.basePackage("com.dianping.orderdish.webapi"))//controller路径
        .paths(PathSelectors.any()).build();
    }

    private ApiInfo buildApiInf() {
        return new ApiInfoBuilder()
        .title("RestAPI Docs")
        .termsOfServiceUrl("http://www.github.com/kongchen/swagger-maven-plugin")
        .build();

    }
}