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
<<<<<<< HEAD
>>>>>>> bd5190a0cc61cd37e6af235b1c215bbc2c8e86f1
=======
>>>>>>> 8f46cf92239e642cbbe6123312e62e5f8d5fd732

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
