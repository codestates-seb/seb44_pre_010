package com.rainbow.sof.global.error;

import lombok.Getter;

public enum ExceptionCode {
<<<<<<< HEAD
    USER_NOT_FOUND(404, "Member not found");
=======
    USER_NOT_FOUND(404, "Member not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    USER_EXISTS(409,"USER EXISTS");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
