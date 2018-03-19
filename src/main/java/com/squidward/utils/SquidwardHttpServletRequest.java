package com.squidward.utils;

import org.kohsuke.github.GitHub;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

public class SquidwardHttpServletRequest extends HttpServletRequestWrapper {

    private GitHub gitHub;

    public SquidwardHttpServletRequest(HttpServletRequest request) {
        super(request);
    }

    public void setGitHub(GitHub gitHub) {
        this.gitHub = gitHub;
    }

    public GitHub getGitHub() {
        return gitHub;
    }
}
