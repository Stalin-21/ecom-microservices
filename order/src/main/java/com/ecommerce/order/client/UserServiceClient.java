package com.ecommerce.order.client;

import com.ecommerce.order.dto.ProductResponse;
import com.ecommerce.order.dto.UserResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "user-service")
public interface UserServiceClient {

    @GetMapping("/api/users/{id}")
    UserResponse getUserDetails(@PathVariable String id);

}