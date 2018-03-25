package com.squidward.utils;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class GithubPayload {

    private String before;
    private String after;
    List<Commit> commits;
    private Repository repository;

    @Getter @Setter
    public static class Commit {

        private String id;
        private String message;
        private String url;
    }

    @Getter @Setter
    public static class Repository {

        private int id;
        private String name;
    }
}
