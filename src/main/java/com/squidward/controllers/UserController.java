package com.squidward.controllers;

import com.squidward.beans.User;
import com.squidward.configs.ApplicationConfig;
import com.squidward.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;
import java.util.Optional;

@Controller
@RequestMapping("/api")
public class UserController {

    private UserService userService;
    private ApplicationConfig applicationConfig;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setApplicationConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @PostMapping("/user/login")
    public void loginUser(HttpServletResponse response, @RequestBody User user) {
        Optional<String> jwt = userService.loginUser(user);
        if (jwt.isPresent()) {

            Cookie jwtCookie = new Cookie(applicationConfig.getTokenName(), jwt.get());
            jwtCookie.setPath("/");
            response.addCookie(jwtCookie);

        } else {

            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @PostMapping("/user/register")
    public void registerUser(HttpServletResponse response, @RequestBody User user) {
        if(!userService.registerUser(user)) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        }
    }
}
