package com.maids.LibraryManagementSystem.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
//import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Configuration;

/**
 *
 * @author Adnan
 */
@Configuration
@OpenAPIDefinition(
     info = @Info(
                title = "Library Management System",
                version = "1.0",
                description = "Api Documentation"
))
public class SwaggerConfig {

}
