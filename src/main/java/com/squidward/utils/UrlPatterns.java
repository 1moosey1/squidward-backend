package com.squidward.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class UrlPatterns {

    @Value("${url.patterns.redirect.url}")
    private String oAuthRedirect;

    @Value("${url.patterns.excluded.urls}")
    private String[] excludedUrls;

    public String[] getExcludedUrls() {
        return excludedUrls;
    }

    public String getOAuthRedirect() {
        return oAuthRedirect;
    }

    public boolean findExcludedMatch(String uri) {

        for (String excludedUrl : excludedUrls) {
            if (uri.startsWith(excludedUrl)) {
                return true;
            }
        }

        return false;
    }
}
