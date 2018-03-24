package com.example.wildflyservicediscovery.rest;


import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Service;

import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


@Path("/")
public class ServiceConsumerEndpoint {

    @GET
    @Produces("application/json")
    @Path("/services")
    public Response getServices(@NotNull @QueryParam("service") String serviceName) {

        ConsulClient consulClient = new ConsulClient("localhost");
        com.ecwid.consul.v1.Response<Map<String, Service>> agentServices = consulClient.getAgentServices();
        System.out.println("agentServices = " + agentServices);

        List<Service> services = agentServices.getValue().values().stream()
                .filter(service -> service.getService().equals(serviceName))
                .collect(Collectors.toList());
        System.out.println("services = " + services);

        return Response.ok(services).build();
    }

    @GET
    @Produces("text/plain")
    @Path("/hello")
    public Response forward() {

        ConsulClient consulClient = new ConsulClient("localhost");
        String serviceUrl = consulClient.getAgentServices()
                .getValue().values().stream()
                .filter(service -> service.getService().equals("hello-world-service"))
                .findAny()
                .map(service -> service.getAddress() + ":" + service.getPort())
                .orElseThrow(IllegalStateException::new);

        return Response.ok("Please call " + serviceUrl).build();
    }

}