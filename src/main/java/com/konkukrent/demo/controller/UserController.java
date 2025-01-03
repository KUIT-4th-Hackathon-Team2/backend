package com.konkukrent.demo.controller;

import com.konkukrent.demo.dto.*;
import com.konkukrent.demo.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.UNAUTHORIZED;

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
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "회원 가입에 성공하였습니다."
            ),
            @ApiResponse(
                    responseCode = "409",
                    description = "이미 해당 학번의 학생이 존재합니다.",
                    content = @Content()
            ),
    })
    @PostMapping("/signup")
    public ResponseEntity<SignupResponse> signup(@RequestBody SignupRequest signupRequest) {
        SignupResponse response = userService.registerUser(signupRequest.getUserName(), signupRequest.getStudentNum(),
                signupRequest.getPassword(), signupRequest.getRole());
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "로그인",
            description = "로그인합니다."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "로그인에 성공하였습니다."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "로그인에 실패했습니다.",
                    content = @Content()
            ),
    })
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest, HttpSession session) {
        LoginResponse response = userService.login(loginRequest, session);
        return ResponseEntity.ok(response);
    }

    @Operation(
            summary = "로그아웃",
            description = "로그아웃합니다."
    )
    @ApiResponse(
            responseCode = "204",
            description = "로그아웃에 성공하였습니다."
    )
    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@RequestBody LogoutRequest logoutRequest, HttpSession session) {
        userService.logout(logoutRequest.getStudentNum(), session);
        return ResponseEntity.noContent().build(); // 204 No Content 응답
    }
}

