package pl.webapp.shop.config;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;

@Configuration
class SwaggerConfig {

    private static final String SECURITY_SCHEME_NAME = "JWT Token";
    private static final String API_TITLE = "Shop API";

    @Value("${server.servlet.context-path}")
    private String contextPath;

    @Bean
    OpenAPI api() {
        return new OpenAPI()
                .components(new Components().addSecuritySchemes(SECURITY_SCHEME_NAME, new SecurityScheme()
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer").bearerFormat("JWT")
                        .in(SecurityScheme.In.HEADER)
                        .name("Authorization")))
                .info(new Info().title(API_TITLE).version(extractApiVersion(contextPath)))
                .addSecurityItem(new SecurityRequirement().addList(SECURITY_SCHEME_NAME, Arrays.asList("read", "write")));
    }

    private String extractApiVersion(String contextPath) {
        return contextPath.substring(5, 7);
    }
}
