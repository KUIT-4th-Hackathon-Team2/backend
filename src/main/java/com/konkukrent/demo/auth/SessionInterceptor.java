package com.konkukrent.demo.auth;

import com.konkukrent.demo.dto.LoginResponse;
import com.konkukrent.demo.exception.SessionException;
import com.konkukrent.demo.exception.properties.ErrorCode;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class SessionInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        // 세션에서 사용자 정보 확인
        LoginResponse loginUser = (LoginResponse) request.getSession().getAttribute("loginUser");
        if (loginUser == null) {
            throw new SessionException(ErrorCode.SESSION_EXPIRED);
        }
        return true;
    }
}
