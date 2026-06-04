package com.interservice.consumer.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/rest-template")
public class InstanceController {

    @GetMapping("/instance")
    public String getInstance(){
        RestTemplate restTemplate = new RestTemplate();

        String response = restTemplate.getForObject("http://localhost:8080/instance-info",String.class);

        return response;
    }
}
