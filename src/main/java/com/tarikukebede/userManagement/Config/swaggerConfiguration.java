package com.tarikukebede.userManagement.Config;

import com.tarikukebede.userManagement.Util.Constants;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.*;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spi.service.contexts.SecurityContext;
import springfox.documentation.spring.web.plugins.Docket;
import java.util.Collections;
import java.util.List;


@Configuration
public class swaggerConfiguration {
    public static final String AUTHORIZATION_HEADER = "Authorization";
    private ApiInfo apiInfo() {
        return new ApiInfo("AppUser Manager API",
                "Let's a company manage users.",
                "1.0",
                "Terms of service",
                new Contact("tCat Apps", "www.tcatapps.com", "tcatappsdeveloper@gmail.com"),
                "License of API",
                "API license URL",
                Collections.emptyList());
    }

    @Bean
    public Docket swaggerConfig(){
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .securityContexts(Collections.singletonList(securityContext()))
                .securitySchemes(Collections.singletonList(apiKey()))
                .select()
                .paths(PathSelectors.ant("/**"))
                .apis(RequestHandlerSelectors.basePackage("com.tarikukebede"))
                .build();
    }


    private ApiKey apiKey() {
        return new ApiKey(Constants.LABEL_ACCESS_TOKEN, AUTHORIZATION_HEADER, "header");
    }

    private SecurityContext securityContext() {
        return SecurityContext.builder()
                .securityReferences(defaultAuth())
                .build();
    }

    List<SecurityReference> defaultAuth() {
        AuthorizationScope authorizationScope
                = new AuthorizationScope("global", "accessEverything");
        AuthorizationScope[] authorizationScopes = new AuthorizationScope[1];
        authorizationScopes[0] = authorizationScope;
        return Collections.singletonList(new SecurityReference("JWT", authorizationScopes));
    }
}
