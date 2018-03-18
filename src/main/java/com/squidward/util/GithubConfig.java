package com.squidward.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class GithubConfig {

    @Value(value="https://github.com/login/oauth/access_token")
    private String oauthURI;

    @Value(value="Iv1.d10898f268a22d3b")
    private String clientId;

    @Value(value="500f9eba1d15af0ef06fdeabd8ce66a00801f733")
    private String clientSecret;

    public String getOAuthURI() {
        return oauthURI;
    }

    public void setOAuthURI(String oauthURI) {
        this.oauthURI = oauthURI;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
}
