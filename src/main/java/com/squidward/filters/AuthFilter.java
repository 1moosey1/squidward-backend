package com.squidward.filters;

import com.squidward.configs.GithubConfig;
import com.squidward.utils.SquidwardHttpServletRequest;
import com.squidward.utils.UrlPatterns;
import lombok.extern.slf4j.Slf4j;
import org.kohsuke.github.GitHub;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.util.WebUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
@Component
public class AuthFilter extends OncePerRequestFilter {

    private UrlPatterns urlPatterns;
    private GithubConfig githubConfig;

    @Autowired
    public void setUrlPatterns(UrlPatterns urlPatterns) {
        this.urlPatterns = urlPatterns;
    }

    @Autowired
    public void setGithubConfig(GithubConfig githubConfig) {
        this.githubConfig = githubConfig;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest httpServletRequest,
                                    HttpServletResponse httpServletResponse,
                                    FilterChain filterChain) throws IOException, ServletException {
        log.debug("Applying Auth Filter");

        SquidwardHttpServletRequest requestWrapper = new SquidwardHttpServletRequest(httpServletRequest);
        if (!urlPatterns.findExcludedMatch(httpServletRequest.getRequestURI())) {

            Cookie oAuthCookie = WebUtils.getCookie(httpServletRequest, githubConfig.getTokenParam());
            GitHub gitHub;

            try {

                String oAuthToken = oAuthCookie.getValue();
                gitHub = GitHub.connectUsingOAuth(oAuthToken);
                gitHub.getMyself().getLogin();
                requestWrapper.setGitHub(gitHub);

            } catch (IOException | NullPointerException e) {

                log.error(e.getMessage());
                handleUnauthorized(httpServletResponse);
                return;
            }
        }

        filterChain.doFilter(requestWrapper, httpServletResponse);
    }

    private void handleUnauthorized(HttpServletResponse httpServletResponse) throws IOException {
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpServletResponse.getWriter().write("Unauthorized");
    }
}
