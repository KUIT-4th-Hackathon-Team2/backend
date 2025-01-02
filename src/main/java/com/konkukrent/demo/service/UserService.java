package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.LoginRequest;
import com.konkukrent.demo.dto.LoginResponse;
import com.konkukrent.demo.dto.SignupResponse;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.entity.enums.Role;
import com.konkukrent.demo.repository.UserRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;

    public SignupResponse registerUser(String userName,
                                       Long studentNum,
                                       String password,
                                       String role) {
        User newUser = User.builder()
                .name(userName)
                .password(password)
                .studentNum(studentNum)
                .role(Role.valueOf(role))
                .build();
        User savedUser = userRepository.save(newUser);

        return SignupResponse.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getName())
                .studentNum(savedUser.getStudentNum())
                .role(String.valueOf(savedUser.getRole()))
                .build();
    }

    // TODO: 로그인 실패 시 오류 커스텀
    public LoginResponse validateUser(LoginRequest loginRequest) {
        return userRepository.findByStudentNum(loginRequest.getStudentNum())
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> LoginResponse.builder()
                        .userId(user.getId())
                        .userName(user.getName())
                        .studentNum(user.getStudentNum())
                        .role(String.valueOf(user.getRole()))
                        .build())
                .orElseThrow(() -> new RuntimeException("Invalid username or password"));
    }

    /*public User findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        return user;
    }*/
}
