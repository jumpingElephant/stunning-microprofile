package com.example.wildflymicroprofile.boundary;

import com.example.wildflymicroprofile.persistence.Greeting;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mongodb.morphia.Datastore;
import org.wildfly.swarm.topology.Advertise;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.URI;

@Path("/hello")
@Advertise("hello-world-service")
public class HelloWorldEndpoint {

    @Inject
    @ConfigProperty(name = "SERVICE_NAME", defaultValue = "unknown")
    private String serviceName;

    @Inject
    private Datastore datastore;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces("text/plain")
    public Response doGet(@QueryParam("name") @NotNull String name) {

        URI serviceUri = uriInfo.getBaseUri();

        Greeting greeting = datastore.get(Greeting.class, serviceName);

        if (greeting == null) {
            throw new IllegalStateException("Cannot find myself");
        }

        String message = new StringBuilder()
                .append("Hi ").append(name).append(", \n")
                .append(greeting.getText()).append(", \n")
                .append("I am on port ").append(serviceUri.getPort())
                .toString();
        return Response.ok(message).build();
    }

}