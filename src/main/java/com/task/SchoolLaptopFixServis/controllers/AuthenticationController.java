package com.task.SchoolLaptopFixServis.controllers;

import com.task.SchoolLaptopFixServis.models.User;
import com.task.SchoolLaptopFixServis.requests.RegisterAndAuthenticateRequest;
import com.task.SchoolLaptopFixServis.responses.LoginResponse;
import com.task.SchoolLaptopFixServis.services.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("laptopServisApi/auth")
@CrossOrigin("*")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public User registerUser(@RequestBody RegisterAndAuthenticateRequest request) {
        return authenticationService.registerUser(request.getUsername(),request.getPassword());
    }

    @PostMapping("/login")
    public LoginResponse loginUser(@RequestBody RegisterAndAuthenticateRequest request) {
        return authenticationService.loginUser(request.getUsername(),request.getPassword());
    }
}
