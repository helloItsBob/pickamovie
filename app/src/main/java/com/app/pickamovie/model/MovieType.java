package com.app.pickamovie.model;

public enum MovieType {
    MOVIE("movie"),
    SERIES("series");

    private final String type;

    MovieType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }
}
