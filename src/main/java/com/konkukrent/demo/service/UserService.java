package com.konkukrent.demo.service;

import java.util.Optional;
import
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

    public User authenticateUser(LoginRequestDTO loginRequestDTO) {
        User user = userRepository.findByUserId(loginRequestDTO.getUserId());

        if (user != null && user.getPassword().equals(loginRequestDTO.getPassword())) {
            return user;
        }
        return null;
    }

    public User registerUser(String name, String email, String socialId, SocialType socialType,
                             Role role) {
        User user = User.builder()
                .role(role)
                .name(name)
                .build();
        return userRepository.save(user);
    }

    public User findByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
    }

    public UserInfoResponse getUserInfo(User user) {
        return UserInfoResponse.from(user);
    }

    public void withdraw(Long userId, Optional<String> accessToken, Optional<String> refreshToken) {
        User user = findUserById(userId);
        user.delete();

        String access = accessToken
                .orElseThrow(() -> new UserException(ErrorCode.SECURITY_INVALID_ACCESS_TOKEN));
        String refresh = refreshToken
                .orElseThrow(() -> new UserException(ErrorCode.REFRESH_TOKEN_REQUIRED));

        jwtService.isTokenValid(refresh);
        jwtService.isTokenValid(access);
        jwtService.deleteRefreshToken(refresh);
        jwtService.invalidAccessToken(access);
    }

    public User findUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new UserException(ErrorCode.USER_NOT_FOUND));
        return user;
    }

    
}
