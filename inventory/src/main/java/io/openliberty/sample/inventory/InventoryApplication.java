package io.openliberty.sample.inventory;


import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;
import org.eclipse.microprofile.openapi.annotations.servers.ServerVariable;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

@ApplicationPath("inventory")
@OpenAPIDefinition(
        info = @Info(
                title = "Inventory app",
                version = "1.0",
                description = "App for storing JVM system properties of various hosts."
        ),
        servers = {
                @Server(url = "http://localhost:{port}", description = "Simple Open Liberty.", variables = {
                        @ServerVariable(
                                name = "port", description = "Server HTTP port.", defaultValue = "9081"
                        )
                })
        }
)
public class InventoryApplication extends Application {

}