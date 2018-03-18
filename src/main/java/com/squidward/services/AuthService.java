package com.squidward.services;

import com.squidward.util.GithubConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthService {

    private GithubConfig githubConfig;

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    public String login(String code) {
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("client_id", githubConfig.getClientId());
        map.add("client_secret", githubConfig.getClientSecret());
        map.add("code", code);

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

        ResponseEntity<String> response = restTemplate.postForEntity(
                githubConfig.getOAuthURI(),
                request,
                String.class
        );

        response.getStatusCode();
        return "login is working" + response.getStatusCode() + response.getBody() + response.getHeaders().toString();
    }
}