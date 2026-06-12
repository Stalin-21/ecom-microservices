package com.ecommerce.user.controller;

import com.ecommerce.user.dto.UserRequest;
import com.ecommerce.user.dto.UserResponse;
import com.ecommerce.user.service.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    //Inject user services
    @Autowired
    private UserServices userServices;


    //Get all users
    @GetMapping
    public ResponseEntity<List<UserResponse>> getUsers() {
        return ResponseEntity.ok(userServices.fetchAllUsers());
    }


    //Get a single user
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getSingleUser(@PathVariable String id) {
        return userServices.getUserById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }


    //Create a user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody UserRequest userRequest) {
        userServices.addUser(userRequest);
        return ResponseEntity.ok("User Created Successfully");
    }
    //Update user details
    @PutMapping("/{id}")
    public ResponseEntity<String> updateUser(@PathVariable String id, @RequestBody UserRequest updatedUserRequest) {
        boolean updated = userServices.userUpdate(id, updatedUserRequest);
        if (updated)
            return ResponseEntity.ok("User Updated Successfully");
        return ResponseEntity.notFound().build();
    }
}
