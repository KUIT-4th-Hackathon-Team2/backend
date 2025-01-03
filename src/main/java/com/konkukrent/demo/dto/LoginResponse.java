package com.konkukrent.demo.dto;

import com.konkukrent.demo.entity.User;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Long userId;
    private String userName;
    private Long studentNum;
    private String role;

    public static LoginResponse from(User user) {
        return LoginResponse.builder()
                .userId(user.getId())
                .userName(user.getName())
                .studentNum(user.getStudentNum())
                .role(user.getRole().name())
                .build();
    }
}