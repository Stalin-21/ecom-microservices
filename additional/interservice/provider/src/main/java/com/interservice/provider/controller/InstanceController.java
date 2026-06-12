package com.interservice.provider.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InstanceController {

    @Value("${server.port}")
    public String port;

    public final String instanceId = java.util.UUID.randomUUID().toString();

    @GetMapping("/instance-info")
    public String getInstanceInfo(){
        System.out.println("Request received from port 8081");
        return "Instance running on port : "+port+ ". Instance Id : "+ instanceId;
    }
}

