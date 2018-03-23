package com.squidward.configs;

import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Setter
@Component
public class ApplicationConfig {

    @Value("${application.issuer}")
    private String issuer;

    @Value("${application.token.expiration}")
    private long tokenExpiration;

    @Value("${application.token.name}")
    private String tokenName;

    @Value("${url.patterns.redirect.url}")
    private String postOAuthRedirect;

    @Value("${application.webhook}")
    private String webhook;

    @Value("${url.patterns.excluded.urls}")
    private String[] excludedUrls;

    public boolean findExcludedMatch(String uri) {

        for (String excludedUrl : excludedUrls) {
            if (uri.startsWith(excludedUrl)) {
                return true;
            }
        }

        return false;
    }
}
