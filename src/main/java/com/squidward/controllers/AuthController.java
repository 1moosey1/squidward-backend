package com.squidward.controllers;

import com.squidward.configs.ApplicationConfig;
import com.squidward.services.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthService authService;
    private ApplicationConfig appConfig;

    @Autowired
    public void setAuthService(AuthService authSerivce) {
        this.authService = authSerivce;
    }

    @Autowired
    public void setApplicationConfig(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @GetMapping(value = "/oauth/{email}")
    public void login(
            HttpServletResponse httpServletResponse,
            @PathVariable("email") String email,
            @RequestParam("code") String code) throws IOException {

        boolean accessGranted = authService.getAccess(code, email);
        httpServletResponse.sendRedirect(appConfig.getPostOAuthRedirect());

        if (!accessGranted) {
            httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
    }
}