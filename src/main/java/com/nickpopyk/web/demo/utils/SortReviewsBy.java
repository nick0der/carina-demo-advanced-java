package com.nickpopyk.web.demo.utils;

public enum SortReviewsBy {
    NEWEST("Newest first"),
    OLDEST("Oldest first"),
    RATING("Best rating");

    private final String value;

    SortReviewsBy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
