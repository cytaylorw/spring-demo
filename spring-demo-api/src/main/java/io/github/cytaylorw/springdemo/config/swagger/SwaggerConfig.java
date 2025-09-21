package io.github.cytaylorw.springdemo.config.swagger;

import java.util.Arrays;
import java.util.List;

import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.security.SecurityRequirement;
import io.swagger.v3.oas.models.security.SecurityScheme;
import io.swagger.v3.oas.models.servers.Server;

/**
 * Swagger configuration
 * 
 * @author Taylor Wong
 *
 */
@Configuration
public class SwaggerConfig {

    /**
     * Swagger properties bean
     * 
     * @return
     */
    @ConfigurationProperties(prefix = "springdoc.properties")
    @Bean
    public SwaggerProperties swaggerProperties() {
        return new SwaggerProperties();
    }

    /**
     * OpenAPI configuration bean
     * 
     * @return
     */
    @Bean
    public OpenAPI demoOpenAPI() {


        Info info = new Info().title("Spring Demo").version("0.0.1").description("Spring demo API endpoints.");

        Components components = new Components().addSecuritySchemes("Bearer Token",
                new SecurityScheme().name("Bearer Token")
                        .type(SecurityScheme.Type.HTTP)
                        .scheme("bearer")
                        .bearerFormat("JWT"));

        SecurityRequirement securityRequirement = new SecurityRequirement().addList("Bearer Token",
                Arrays.asList("read", "write"));

        List<Server> servers = this.swaggerProperties().getServers().stream().map(s -> {
            Server server = new Server();
            server.setUrl(s.getUrl());
            server.setDescription(s.getDescription());
            return server;
        }).toList();

        return new OpenAPI().info(info).components(components).addSecurityItem(securityRequirement).servers(servers);
    }

    /**
     * OpenAPI group: Demo APIs
     * 
     * @return
     */
    @Bean
    public GroupedOpenApi demoApi() {
        String packagesToscan[] = { "io.github.cytaylorw.springdemo.domain" };
        return GroupedOpenApi.builder().group("1.demo").displayName("Demo API").packagesToScan(packagesToscan).build();
    }

    /**
     * OpenAPI group: actuator APIs
     * 
     * @return
     */
    @Bean
    public GroupedOpenApi actuatorApi() {
        String pathsToMatch[] = { "/actuator/**", "/login" };
        return GroupedOpenApi.builder()
                .group("2.actuator")
                .displayName("Actuator API")
                .pathsToMatch(pathsToMatch)
                .build();
    }
}
