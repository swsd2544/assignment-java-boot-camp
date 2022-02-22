package com.example.javaassignment.shoppingapi.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest registerRequest) throws Exception {
        if (registerRequest.getEmail() != null && registerRequest.getPassword() != null) {
            User user = userService.createUser(registerRequest.getEmail(), registerRequest.getPassword(),
                    registerRequest.getPhone(), registerRequest.getFirstName(), registerRequest.getLastName());
            String token = userService.generateJsonWebToken(user);
            return new AuthResponse(token);
        }
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
    }
}
