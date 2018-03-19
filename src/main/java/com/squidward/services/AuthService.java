package com.squidward.services;

import com.squidward.beans.User;
import com.squidward.utils.GithubConfig;
import com.squidward.utils.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Slf4j
@Service
public class AuthService {

    private UserService userService;
    private GithubConfig githubConfig;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    public Parameters login(String code) {

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add(githubConfig.getClientIdParam(), githubConfig.getClientId());
        map.add(githubConfig.getClientSecretParam(), githubConfig.getClientSecret());
        map.add(githubConfig.getCodeParam(), code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response =
                restTemplate.postForEntity(githubConfig.getOAuthURI(), request, String.class);

        response.getHeaders();
        Parameters parameters = new Parameters(response.getBody());

        try {

            GitHub gitHub = GitHub.connectUsingOAuth(parameters.getParameter(githubConfig.getTokenParam()));
            String username = gitHub.getMyself().getLogin();
            String email = gitHub.getMyself().getEmail();

            if (!userService.doesUserExist(username)) {

                User user = new User();
                user.setUsername(username);
                user.setEmail(email);
                user = userService.saveUser(user);
                log.debug(user.toString());
            }

        } catch (IOException | NullPointerException e) {
            
            log.error(e.getMessage());
            return new Parameters();
        }

        return parameters;
    }
}