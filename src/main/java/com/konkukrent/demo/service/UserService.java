package com.konkukrent.demo.service;

import com.konkukrent.demo.dto.LoginRequest;
import com.konkukrent.demo.dto.LoginResponse;
import com.konkukrent.demo.dto.SignupRequest;
import com.konkukrent.demo.dto.SignupResponse;
import com.konkukrent.demo.entity.User;
import com.konkukrent.demo.entity.enums.Role;
import com.konkukrent.demo.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

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
                .userName(userName)
                .password(password)
                .studentNum(studentNum)
                .role(Role.valueOf(role))
                .build();
        User savedUser = userRepository.save(newUser);

        return SignupResponse.builder()
                .userId(savedUser.getId())
                .userName(savedUser.getUserName())
                .studentNum(savedUser.getStudentNum())
                .role(String.valueOf(savedUser.getRole()))
                .build();
    }

    public LoginResponse validateUser(LoginRequest loginRequest) {
        return userRepository.findByStudentNum(loginRequest.getStudentNum())
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> LoginResponse.builder()
                        .userId(user.getId())
                        .userName(user.getUserName())
                        .studentNum(user.getStudentNum())
                        .role(String.valueOf(user.getRole()))
                        .build())
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));
    }

    /*public User findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        return user;
    }*/
}
