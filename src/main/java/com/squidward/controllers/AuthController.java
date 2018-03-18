package com.squidward.controllers;

import com.squidward.services.AuthService;
import com.squidward.utils.GithubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class AuthController {

    private GithubConfig githubConfig;
    private AuthService authSerivce;

    @Autowired
    public void setAuthSerivce(AuthService authSerivce) {
        this.authSerivce = authSerivce;
    }

    @GetMapping(value = "/login")
    public String login(@RequestParam("code") String code) {
        return authSerivce.login(code);
    }
}