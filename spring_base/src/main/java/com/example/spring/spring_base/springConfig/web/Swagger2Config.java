package com.example.spring.spring_base.springConfig.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Config {
    @Autowired
    Environment environment;
    @Value("${spring.profiles.active}")
    String profile;
    @Bean
    public Docket createRestApi() {
        if(profile.equals("dev")){
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.spring.spring_base.web.controller"))
                    .paths(PathSelectors.any())
                    .build();
        }else {
            return new Docket(DocumentationType.SWAGGER_2)
                    .apiInfo(apiInfo())
                    .select()
                    .apis(RequestHandlerSelectors.basePackage("com.example.spring.spring_base.web.controller"))
                    //添加路径过滤，设置为全部都不符合
                    .paths(PathSelectors.none())
                    .build();
        }

    }
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("Spring Boot中使用Swagger2构建RESTful APIs")
                .description("更多Spring Boot相关文章请关注：http://www.xstoumall.com/")
                .termsOfServiceUrl("http://www.xstoumall.com/")
                .contact("rongly")
                .version("1.0")
                .build();
    }
}