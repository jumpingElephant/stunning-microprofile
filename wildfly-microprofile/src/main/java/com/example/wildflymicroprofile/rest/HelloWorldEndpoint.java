package com.example.wildflymicroprofile.rest;

import com.example.wildflymicroprofile.persistence.Greeting;
import org.eclipse.microprofile.config.Config;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.mongodb.morphia.Datastore;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@ApplicationScoped
@Path("/hello")
public class HelloWorldEndpoint {

    @Inject
    @ConfigProperty(name = "SERVICE_NAME", defaultValue = "unknown")
    private String serviceName;

    @Inject
    private Datastore datastore;

    @GET
    @Produces("text/plain")
    public Response doGet(@QueryParam("name") @NotNull String name) {

        Greeting greeting = datastore.get(Greeting.class, serviceName);

        if (greeting == null) {
            throw new IllegalStateException("Cannot find myself");
        }

        return Response
                .ok("Hi " + name + ", \n" + greeting.getText())
                .build();
    }

}