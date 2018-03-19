package com.squidward.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AuthPatterns {

    @Value("${excluded.urls}")
    private String[] excludedUrls;

    public String[] getExcludedUrls() {
        return excludedUrls;
    }

    public boolean findMatch(String uri) {

        for (String excludedUrl : excludedUrls) {
            if (uri.startsWith(excludedUrl)) {
                return true;
            }
        }

        return false;
    }
}
