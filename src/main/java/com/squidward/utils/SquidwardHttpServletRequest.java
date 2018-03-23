package com.squidward.utils;

import lombok.Getter;
import lombok.Setter;
import org.kohsuke.github.GitHub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

@Getter @Setter
public class SquidwardHttpServletRequest extends HttpServletRequestWrapper {

    private GitHub gitHub;

    public SquidwardHttpServletRequest(HttpServletRequest request) {
        super(request);
    }
}
