package com.konkukrent.demo.entity;

import com.konkukrent.demo.entity.enums.Role;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 10)
    private String userName;

    @Column(nullable = false)
    private int studentNum;

    @Column(nullable = false, length = 255)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Role role;

    @Builder
    private User(String userName, int studentNum, String password, Role role) {
        this.userName = userName;
        this.studentNum = studentNum;
        this.password = password;
        this.role = role;
    }

}
