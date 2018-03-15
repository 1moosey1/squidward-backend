package com.squidward.controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
    public class Login {
        @RequestMapping(value = "/login", method = RequestMethod.POST /*produces = MediaType.APPLICATION_JSON_VALUE*/)
        public String login() {
            return "login is working";
        }


}
