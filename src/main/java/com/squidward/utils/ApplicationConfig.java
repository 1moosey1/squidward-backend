package com.squidward.utils;

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
}
