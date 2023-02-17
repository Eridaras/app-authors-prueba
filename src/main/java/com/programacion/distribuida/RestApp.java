package com.programacion.distribuida;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.info.License;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@OpenAPIDefinition(
        info = @Info(
                title = "AUTHOR API",
                description = "Author Servicio",
                version = "1.0.0",
                license = @License(
                        name = "MIT"
                )

        ),
        tags = {
                @Tag(name = "AUTHORS", description = "authors")
        }
)
@ApplicationPath("/")
public class RestApp extends Application {
}