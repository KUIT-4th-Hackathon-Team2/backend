package com.konkukrent.demo.controller;

import com.konkukrent.demo.entity.User;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

public class UserController {
    @PostMapping("/login")
    public String LoginV4(@ModelAttribute User loggedInUser,
                          HttpServletRequest request) {
        log.info("LoginV4");
        User user = userRepository.findByUserId(loggedInUser.getUserId());
        if (user != null && user.isSameUser(loggedInUser)) {
            HttpSession session = request.getSession();
            session.setAttribute(USER_SESSION_KEY, loggedInUser);
            return "redirect:/";
        }
        return "redirect:/user/loginFailed";
    }
}
