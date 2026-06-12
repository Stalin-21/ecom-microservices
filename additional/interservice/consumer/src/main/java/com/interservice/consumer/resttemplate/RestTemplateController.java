package com.interservice.consumer.resttemplate;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/resttemplate")

public class RestTemplateController {
    private final RestTemplateClient restTemplateClient;

   public RestTemplateController (RestTemplateClient restTemplateClient){
       this.restTemplateClient = restTemplateClient;
   }

    @GetMapping("/instance")
    public String getInstance(){
       return restTemplateClient.getInstanceInfo();
    }

}
