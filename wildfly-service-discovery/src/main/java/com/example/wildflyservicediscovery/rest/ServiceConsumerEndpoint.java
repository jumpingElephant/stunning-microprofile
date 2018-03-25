package com.example.wildflyservicediscovery.rest;


import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Service;
import com.example.wildflyservicediscovery.control.ServiceName;

import javax.inject.Inject;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/")
public class ServiceConsumerEndpoint {

    @Inject
    @ServiceName("hello-world-service")
    private Service helloWorldService;

    @Context
    private UriInfo uriInfo;

    @GET
    @Produces("application/json")
    @Path("/services")
    public Response getServices(@NotNull @QueryParam("service") String serviceName) {

        ConsulClient consulClient = new ConsulClient("localhost");
        com.ecwid.consul.v1.Response<Map<String, Service>> agentServices = consulClient.getAgentServices();

        List<Service> services = agentServices.getValue().values().stream()
                .filter(service -> service.getService().equals(serviceName))
                .collect(Collectors.toList());

        return Response.ok(services).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public Response forward(@QueryParam("name") String name) throws MalformedURLException, URISyntaxException {

        String servicePath = "http://" + helloWorldService.getAddress() + ":" + helloWorldService.getPort();

        Client client = ClientBuilder.newClient();
        WebTarget target = client.target(servicePath).path("/hello").queryParam("name", name);
        Response response = target.request(MediaType.TEXT_PLAIN).get();
        String greeting = response.readEntity(String.class) + "\nRedirected by " + uriInfo.getPath();

        return Response.ok(greeting).build();
    }

}