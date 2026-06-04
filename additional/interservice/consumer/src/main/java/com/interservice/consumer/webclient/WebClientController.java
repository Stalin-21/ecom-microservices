package com.interservice.consumer.webclient;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/web-client")
public class WebClientController {

    private final ProviderWebClient providerWebClient;

    @GetMapping("/instance")
    public Mono<String> getInstance(){
//        WebClient webclient = WebClient.create();
//
//        Mono<String> response = webclient.get()
//                .uri("http://localhost:8080/instance-info")
//                .retrieve()
//                .bodyToMono(String.class);

        return providerWebClient.getInstanceInfo();
    }
}
