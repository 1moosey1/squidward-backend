package com.squidward.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class GithubConfig {

    @Value(value="${github.oauth.url}")
    private String oAuthURI;

    @Value(value="${github.oauth.access.url}")
    private String oAuthAccessURI;

    @Value("${github.oauth.redirect}")
    private String oAuthRedirectURI;

    @Value("${github.oauth.redirect.param}")
    private String oAuthRedirectParam;

    @Value(value="${github.oauth.client.id}")
    private String clientId;

    @Value(value="${github.oauth.client.id.param}")
    private String clientIdParam;

    @Value(value="${github.oauth.client.secret}")
    private String clientSecret;

    @Value(value="${github.oauth.client.secret.param}")
    private String clientSecretParam;

    @Value(value="${github.oauth.token.name}")
    private String tokenParam;

    @Value(value="${github.oauth.code.param}")
    private String codeParam;

    @Value(value="${github.oauth.scope}")
    private String[] scopes;

    @Value(value="${github.oauth.scope.param}")
    private String scopeParam;
}
