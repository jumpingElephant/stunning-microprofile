package com.example.wildflyservicediscovery.control;

import com.ecwid.consul.v1.ConsulClient;
import com.ecwid.consul.v1.agent.model.Service;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;

@ApplicationScoped
public class ConsulServiceProvider {

    @Produces
    public Service produceService(InjectionPoint injectionPoint) {

        String serviceName = injectionPoint.getAnnotated().getAnnotation(ServiceName.class).value();

        ConsulClient consulClient = new ConsulClient("localhost");
        Service service = consulClient.getAgentServices()
                .getValue().values().stream()
                .filter(agentService -> agentService.getService().equals(serviceName))
                .findAny()
                .orElseThrow(IllegalStateException::new);

        return service;
    }

}
