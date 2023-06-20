package com.rainbow.sof.domain.user.util;

import lombok.Getter;

public enum CustomEnumUri {


    USER_DEFAULT_URL("/api/v1/users"),
    DELETE_ACTION_URL("/signup");
    @Getter
    private int status;

    @Getter
    private String uri;

    CustomEnumUri(String uri) {
        this.uri = uri;
    }
}
