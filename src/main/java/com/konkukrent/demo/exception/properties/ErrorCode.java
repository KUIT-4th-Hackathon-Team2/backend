package com.konkukrent.demo.exception.properties;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import static org.springframework.http.HttpStatus.*;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    // 400
    ADMIN_PASSWORD_INCORRECT(BAD_REQUEST, "잘못된 비밀번호입니다."),

    // 401
    LOGIN_FAIL(UNAUTHORIZED, "로그인에 실패했습니다."),
    SESSION_EXPIRED(UNAUTHORIZED, "세션이 만료되었습니다."),

    //404
    RESERVATION_NOT_FOUND(NOT_FOUND, "reservation을 찾을 수 없습니다."),
    RENTAL_NOT_FOUND(NOT_FOUND, "renatl을 찾을 수 없습니다."),

    // 409
    STUDENT_NUM_ALREADY(CONFLICT, "이미 해당 학번의 학생이 존재합니다."),

    // 500
    SERVER_ERROR(INTERNAL_SERVER_ERROR, "예상치 못한 서버 에러가 발생하였습니다.");


    private final HttpStatus httpStatus;
    private final String message;
}
