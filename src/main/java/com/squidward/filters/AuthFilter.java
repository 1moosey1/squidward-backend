package com.squidward.filters;

import com.squidward.configs.ApplicationConfig;
import com.squidward.configs.GithubConfig;
import com.squidward.services.UserService;
import com.squidward.utils.JWTUtil;
import com.squidward.utils.SquidwardHttpServletRequest;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    private UserService userService;
    private ApplicationConfig appConfig;
    private GithubConfig githubConfig;
    private JWTUtil jwtUtil;

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Autowired
    public void setApplicationConfig(ApplicationConfig appConfig) {
        this.appConfig = appConfig;
    }

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    @Autowired
    public void setJwtUtil(JWTUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse,
            FilterChain filterChain) throws IOException, ServletException {
        log.debug("Applying Auth Filter");

        SquidwardHttpServletRequest requestWrapper = new SquidwardHttpServletRequest(httpServletRequest);
        if (!appConfig.findExcludedMatch(httpServletRequest.getRequestURI())) {

            GitHub gitHub;
            Claims claims = null;
            String jwtToken = httpServletRequest.getHeader(appConfig.getTokenName());
            log.debug(jwtToken);

            try {

                claims = jwtUtil.parseJWT(jwtToken.split(" ")[1]);
                String email = claims.getSubject();

                if (!userService.doesUserExistByEmail(email)) {
                    handleUnauthorized(httpServletResponse);
                    return;
                }

                Optional<String> oAuthTokenOptional = userService.getUserOAuthToken(email);
                if (oAuthTokenOptional.isPresent()) {
                    gitHub = GitHub.connectUsingOAuth(oAuthTokenOptional.get());
                } else {
                    handleOAuthRedirect(httpServletResponse, email);
                    return;
                }

                requestWrapper.setGitHub(gitHub);

            } catch (IOException e) {

                log.error(e.getMessage());
                handleOAuthRedirect(httpServletResponse, claims.getSubject());
                return;

            } catch(JwtException | NullPointerException e) {

                log.error(e.getMessage());
                handleUnauthorized(httpServletResponse);
                return;
            }
        }

        filterChain.doFilter(requestWrapper, httpServletResponse);
    }

    private void handleUnauthorized(HttpServletResponse httpServletResponse) {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
    }

    private void handleOAuthRedirect(HttpServletResponse httpServletResponse, String email)
            throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);

        UriComponentsBuilder uriComponentsBuilder
                = UriComponentsBuilder.fromHttpUrl(githubConfig.getOAuthAccessURI());
        uriComponentsBuilder.queryParam(githubConfig.getClientIdParam(), githubConfig.getClientId());
        uriComponentsBuilder.queryParam(githubConfig.getScopeParam(), githubConfig.getScopes());
        uriComponentsBuilder.queryParam(githubConfig.getOAuthRedirectParam(),
                githubConfig.getOAuthRedirectURI() + email);

        String oAuthUrl = uriComponentsBuilder.toUriString();
        httpServletResponse.getWriter().write(oAuthUrl);
        log.debug("Requesting OAuth access for " + email + " at " + oAuthUrl);
    }
}
