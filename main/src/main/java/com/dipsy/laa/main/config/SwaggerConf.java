package com.dipsy.laa.main.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ParameterBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Parameter;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.List;

/**
 * swagger接口配置
 * @author tgp
 */
@Configuration
@EnableSwagger2
public class SwaggerConf {

    @Bean
    public Docket createRestApi() {
        ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name("Authorization")
            .description("Authorization Token")
            .modelRef(new ModelRef("string"))
            .parameterType("header")
            .required(false)
            .build();

        pars.add(tokenPar.build());

        return new Docket(DocumentationType.SWAGGER_2)
            .apiInfo(apiInfo())
            .select()
            .apis(RequestHandlerSelectors.basePackage("com.dipsy.laa"))
            .paths(PathSelectors.any())
            .build()
            .globalOperationParameters(pars);
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
            .title("Laa RESTful APIs")
            .description("")
            .termsOfServiceUrl("")
            .version("1.0.0")
            .build();
    }

}