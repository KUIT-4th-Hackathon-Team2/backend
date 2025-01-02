package com.konkukrent.demo.dto;

import lombok.Getter;

@Getter
public class SignupRequest{
    private String userName;
    private Long studentNum;
    private String password;
    private String role;
}
