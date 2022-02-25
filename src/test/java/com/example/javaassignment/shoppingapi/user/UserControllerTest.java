package com.example.javaassignment.shoppingapi.user;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private TestRestTemplate testRestTemplate;

    @Test
    @DisplayName("Posting /register with no included body should return BAD REQUEST status code")
    void failCaseRegisterNoEmailAndPassword() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(new RegisterRequest(), headers);

        ResponseEntity<AuthResponse> result = testRestTemplate.postForEntity("/register",
                httpEntity, AuthResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    @DisplayName("Posting /register with no included email should return BAD REQUEST status code")
    void failCaseRegisterNoEmail() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("test@test.com");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(req, headers);

        ResponseEntity<AuthResponse> result = testRestTemplate.postForEntity("/register",
                httpEntity, AuthResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    @DisplayName("Posting /register with no included password should return BAD REQUEST status code")
    void failCaseRegisterNoPassword() {
        RegisterRequest req = new RegisterRequest();
        req.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(req, headers);

        ResponseEntity<AuthResponse> result = testRestTemplate.postForEntity("/register",
                httpEntity, AuthResponse.class);
        assertEquals(HttpStatus.BAD_REQUEST, result.getStatusCode());
    }

    @Test
    @DisplayName("Posting /register with included email and password should return OK status code")
    void successCaseRegister() {
        RegisterRequest req = new RegisterRequest();
        req.setEmail("test@test.com");
        req.setPassword("password");
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<RegisterRequest> httpEntity = new HttpEntity<>(req, headers);

        ResponseEntity<AuthResponse> result = testRestTemplate.postForEntity("/register",
                httpEntity, AuthResponse.class);
        assertEquals(HttpStatus.OK, result.getStatusCode());
    }
}