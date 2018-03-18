package com.squidward.filters;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;

@Slf4j
@Component
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) {
        log.debug("Initializing Auth Filter");
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain)
            throws IOException, ServletException {
        log.debug("Applying Auth Filter");
        servletResponse.getWriter().write("You have been Auth Filtered!");
        //filterChain.doFilter(servletRequest, servletResponse);
    }

    @Override
    public void destroy() {
        log.debug("Destroying Auth Filter");
    }
}
