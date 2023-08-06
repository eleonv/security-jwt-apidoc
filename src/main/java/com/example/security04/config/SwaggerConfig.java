package com.example.security04.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;
import io.swagger.v3.oas.models.Components;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@OpenAPIDefinition(
        info = @Info(
                contact = @Contact(
                        name = "Jorge Guerra",
                        email = "jguerrag@unmsm.edu.pe",
                        url = "https://dina.concytec.gob.pe/appDirectorioCTI/VerDatosInvestigador.do?id_investigador=12581"
                ),
                description = "Documentacion OpenApi para Spring Security",
                title = "OpenApi specification - JGUERRA",
                version = "1.0",
                license = @License(
                        name = "Licence name",
                        url = "https://some-url.com"
                ),
                termsOfService = "Terminos de servicio"
        ),
        servers = {
                @Server(
                        description = "Local ENV",
                        url = "http://localhost:8080"
                ),
                @Server(
                        description = "PROD ENV",
                        url = "https://jguerra91.com/cursos"
                )
        },
        security = {
                @SecurityRequirement(
                        name = "bearerAuth"
                )
        }
)
@SecurityScheme(
        name = "bearerAuth",
        description = "JWT auth description",
        scheme = "bearer",
        type = SecuritySchemeType.HTTP,
        bearerFormat = "JWT",
        in = SecuritySchemeIn.HEADER
)
@Configuration
public class SwaggerConfig {
    @Value("${springdoc.api-docs.title}")
    private String title;

    /*@Value("${swagger.apiInfo.description}")
    private String description;

    @Value("${swagger.apiInfo.contact.name}")
    private String nameContact;

    @Value("${swagger.apiInfo.contact.url}")
    private String urlContact;

    @Value("${swagger.apiInfo.contact.email}")
    private String emailContact;

    @Value("${swagger.apiInfo.license}")
    private String license;

    @Value("${swagger.apiInfo.licenseUrl}")
    private String licenseUrl;

    @Value("${swagger.apiInfo.version}")
    private String version;*/

    /*@Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .components(new Components())
                .info(new io.swagger.v3.oas.models.info.Info()
                        .title("SACAViX Spring Boot 3 API ------kjkjk")
                        .version("0.11")
                        .description("Sample app Spring Boot 3 with Swagger")
                        .termsOfService("http://swagger.io/terms/")
                        .license(new io.swagger.v3.oas.models.info.License().name("Apache 2.0").url("http://springdoc.org")));
    }*/
}