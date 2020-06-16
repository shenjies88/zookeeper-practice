package com.shenjies88.practice.zkroute;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * @author shenjies88
 * @since 2020/6/14-4:50 PM
 */
@SpringBootApplication
@RestController
public class ZookeeperRouteApplication {

    @Autowired
    private DiscoveryClient discoveryClient;

    @GetMapping("/zk-route/get-service-url")
    public List<List<ServiceInstance>> getServiceUrl() {
        List<String> services = discoveryClient.getServices();
        List<List<ServiceInstance>> instances = new ArrayList<>();
        services.stream().filter(e -> !"root".equals(e)).forEach(e ->
                instances.add(discoveryClient.getInstances(e))
        );
        return instances;
    }

    public static void main(String[] args) {
        new SpringApplicationBuilder(ZookeeperRouteApplication.class).run(args);
    }
}
