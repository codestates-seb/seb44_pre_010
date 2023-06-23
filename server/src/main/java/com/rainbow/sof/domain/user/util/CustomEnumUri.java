package com.rainbow.sof.domain.user.util;

import lombok.Getter;

public enum CustomEnumUri {

    DEFAULT_URL("/api/v1"),
    USER_DEFAULT_URL("/api/v1/users"),
    DELETE_ACTION_URL("/signup"),
    QUESTION_DEFAULT_URL("/api/v1/questions");
    @Getter
    private int status;

    @Getter
    private String uri;

    CustomEnumUri(String uri) {
        this.uri = uri;
    }
}
