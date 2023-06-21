package com.rainbow.sof.global.error;

import lombok.Getter;

public enum ExceptionCode {
<<<<<<< HEAD
<<<<<<< HEAD
    USER_NOT_FOUND(404, "Member not found");
=======
=======

    INVALID_TOKEN(401,"is not the same token as data"),
    UNAUTHORIZED(401,"invalid token Data"),
>>>>>>> 4bf0b47384ae1e81260a33ae4f7dae3460a75e2f
    USER_NOT_FOUND(404, "Member not found"),
    QUESTION_NOT_FOUND(404, "Question not found"),
    CANNOT_CHANGE_QUESTION(403,"Question can not change"),
    CANNOT_DELETE_QUESTION(403,"Question can not delete"),
    ANSWER_NOT_FOUND(404, "Answer not found"),
    USER_EXISTS(409,"USER EXISTS"),
    USER_MISMATCH(403,"The login user and the author are different");

    @Getter
    private int status;

    @Getter
    private String message;

    ExceptionCode(int code, String message) {
        this.status = code;
        this.message = message;
    }
}
