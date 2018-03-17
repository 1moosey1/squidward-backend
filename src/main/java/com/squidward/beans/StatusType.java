package com.squidward.beans;

public enum StatusType {

    TODO("TODO"),
    IN_PROGRESS("IN PROGRESS"),
    DONE("DONE");

    private final String name;

    StatusType(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}
