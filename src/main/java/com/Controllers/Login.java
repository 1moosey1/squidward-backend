package com.Controllers;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Login {
    @RequestMapping(value = "/login", method = RequestMethod.POST /*produces = MediaType.APPLICATION_JSON_VALUE*/)
    public String login(@RequestParam("username") String username) {
        return "login is working";
    }

    @RequestMapping(value = "/logout")
    public String logout(@RequestParam("username") String username) {
        return "logout successful";
    }

}
