package com.squidward.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.Date;

@Component
public class JWTUtil {

    private ApplicationConfig applicationConfig;
    private GithubConfig githubConfig;

    @Autowired
    public void setApplicationConfig(ApplicationConfig applicationConfig) {
        this.applicationConfig = applicationConfig;
    }

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    private String createJWT(String id, String subject) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(githubConfig.getClientSecret());
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        JwtBuilder builder = Jwts.builder()
                .setId(id)
                .setIssuedAt(now)
                .setSubject(subject)
                .setIssuer(applicationConfig.getIssuer())
                .signWith(signatureAlgorithm, signingKey);

        Date exp = new Date(nowMillis + applicationConfig.getTokenExpiration());
        builder.setExpiration(exp);

        return builder.compact();
    }

    private Claims parseJWT(String jwt) {

        return Jwts.parser()
                .setSigningKey(DatatypeConverter.parseBase64Binary(githubConfig.getClientSecret()))
                .parseClaimsJws(jwt).getBody();
    }
}
