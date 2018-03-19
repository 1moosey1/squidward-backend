package com.squidward.controllers;

import com.squidward.services.AuthService;
import com.squidward.utils.GithubConfig;
import com.squidward.utils.Parameters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/api")
public class AuthController {

    private AuthService authService;
    private GithubConfig githubConfig;

    @Autowired
    public void setAuthService(AuthService authSerivce) {
        this.authService = authSerivce;
    }

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    @GetMapping(value = "/login")
    public ResponseEntity<String> login(HttpServletResponse httpServletResponse,
                                        @RequestParam("code") String code) {

        String tokenParam = githubConfig.getTokenParam();
        Parameters parameters = authService.login(code);

        if (parameters.hasParameter(tokenParam)) {

            Cookie oAuthCookie = new Cookie(tokenParam, parameters.getParameter(tokenParam));
            httpServletResponse.addCookie(oAuthCookie);
            return new ResponseEntity<>(HttpStatus.OK);

        } else {

            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
    }
}