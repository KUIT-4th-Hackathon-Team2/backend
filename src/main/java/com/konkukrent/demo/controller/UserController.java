package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.SignupRequest;
import com.konkukrent.demo.dto.SignupResponse;
import com.konkukrent.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
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
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Users")
@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Operation(
            summary = "회원 가입",
            description = "회원을 가입합니다."
    )
    @ApiResponse(
            responseCode = "200",
            description = "회원 가입에 성공하였습니다."
    )
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
