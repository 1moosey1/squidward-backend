package com.squidward.services;

import com.squidward.utils.GithubConfig;
import com.squidward.utils.Parameters;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;

@Slf4j
@Service
public class AuthService {

    private GithubConfig githubConfig;

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
        return new Parameters(response.getBody());
    }
}