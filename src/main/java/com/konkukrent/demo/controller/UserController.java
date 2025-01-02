package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.SignupRequest;
import com.konkukrent.demo.dto.SignupResponse;
import com.konkukrent.demo.service.UserService;
import io.swagger.v3.oas.annotations.tags.Tag;
import com.konkukrent.demo.dto.LoginRequest;
import com.konkukrent.demo.dto.LoginResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Tag(name = "Users")
@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse response = userService.registerUser(signupRequest.getUserName(), signupRequest.getStudentNum(),
                signupRequest.getPassword(), signupRequest.getRole());
        return ResponseEntity.ok(response);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        LoginResponse response = userService.validateUser(loginRequest);
        return ResponseEntity.ok(response);
    }
}
