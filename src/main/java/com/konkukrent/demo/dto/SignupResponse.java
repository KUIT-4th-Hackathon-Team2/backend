package com.konkukrent.demo.dto;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class SignupResponse{
    private Long userId;
    private String userName;
    private int studentNum;
    private String role;
}
