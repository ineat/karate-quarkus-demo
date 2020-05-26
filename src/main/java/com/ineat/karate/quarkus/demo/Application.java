package com.ineat.karate.quarkus.demo;

import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.enums.SecuritySchemeType;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlow;
import org.eclipse.microprofile.openapi.annotations.security.OAuthFlows;
import org.eclipse.microprofile.openapi.annotations.security.SecurityScheme;
import org.eclipse.microprofile.openapi.annotations.tags.Tag;

@OpenAPIDefinition(
        tags = {
                @Tag(name = "todo", description = "Operations related to todos"),
        },
        info = @Info(
                title = "Todo API",
                version = "1.0.0",
                contact = @Contact(
                        name = "Mathias Deremer-Accettone",
                        email = "mderemeraccetone@ineat.fr"
                )
        )
)
@SecurityScheme(securitySchemeName = "oauth2",
        type = SecuritySchemeType.OAUTH2,
        description = "Authentication needed for this operation",
        flows = @OAuthFlows(
                implicit = @OAuthFlow(authorizationUrl = "http://localhost:8180/auth/realms/karate-quarkus-demo-realm/protocol/openid-connect/auth"
                )
        )
)
public class Application extends javax.ws.rs.core.Application {
}
