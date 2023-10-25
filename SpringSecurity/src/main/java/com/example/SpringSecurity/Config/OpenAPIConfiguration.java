package com.example.SpringSecurity.Config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeIn;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import io.swagger.v3.oas.annotations.servers.Server;

@OpenAPIDefinition(
        info = @Info(
                title = "College Managemant Portal (cmp.io)",
                description = "" +
                        "This is just for learning purpose ...",
                contact = @Contact(
                        name = "Alok",
                        url = "https://www.linkedin.com/in/alokgyadav/",
                        email = "alokyadav.20102@gmail.com"
                ),
                license = @License(
                        name = "SMP Licence",
                        url = "https://github.com/Y-Alok")),
        servers = @Server(url = "http://localhost:8080"))

@SecurityScheme(name = "api",
        scheme = "basic",
        type = SecuritySchemeType.HTTP,
        in = SecuritySchemeIn.HEADER)
public class OpenAPIConfiguration {
}
