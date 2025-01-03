package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.*;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.entity.enums.Role;
import com.konkukrent.demo.exception.UserException;
import com.konkukrent.demo.exception.properties.ErrorCode;
import com.konkukrent.demo.repository.UserRepository;

import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final HttpSession httpSession;

    public SignupResponse registerUser(String userName,
                                       Long studentNum,
                                       String password,
                                       String role) {

        // 이미 존재하는 학번인지 확인
        if (userRepository.existsByStudentNum(studentNum)) {
            throw new UserException(ErrorCode.STUDENT_NUM_ALREADY);
        }

        User newUser = User.builder()
                .name(userName)
                .password(password) // 비밀번호 암호화
                .studentNum(studentNum)
                .role(Role.valueOf(role))
                .build();

        User savedUser = userRepository.save(newUser);

        return SignupResponse.from(savedUser); // 정적 팩토리 메서드 활용
    }

    public LoginResponse login(LoginRequest loginRequest) {
        return userRepository.findByStudentNum(loginRequest.getStudentNum())
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> {
                    LoginResponse loginResponse = LoginResponse.from(user);
                    httpSession.setAttribute("loginUser", loginResponse);
                    httpSession.setMaxInactiveInterval(3600); // 1시간 설정
                    return loginResponse;
                })
                .orElseThrow(() -> new UserException(ErrorCode.LOGIN_FAIL));
    }

    /*public void logout(LogoutRequest logoutRequest) {

        HttpSession session = logoutRequest.getSession();
        if (UserSessionUtils.isLoggedIn(session)) {
            session.invalidate();
        }
    }*/
}
