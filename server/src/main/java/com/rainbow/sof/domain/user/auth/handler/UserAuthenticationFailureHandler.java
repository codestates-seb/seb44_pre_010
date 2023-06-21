package com.rainbow.sof.domain.user.auth.handler;

import com.google.gson.Gson;
import com.rainbow.sof.global.common.ErrorResponse;
import com.rainbow.sof.global.error.ExceptionCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException, ServletException {
        log.error("# Authentication failed: {}", exception.getMessage());
        Gson gson = new Gson();
        extracted(response, gson,exception);

    }

    private static void extracted(HttpServletResponse response, Gson gson, AuthenticationException exception) throws IOException {

        String exceptionMassage=exception.getMessage();
        ErrorResponse errorResponse = getErrorResponse(exceptionMassage);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }

    private static ErrorResponse getErrorResponse(String exceptionMassage) {
        ErrorResponse errorResponse =
                exceptionMassage.equals(ExceptionCode.USER_NOT_FOUND.getMessage()) ?
                        ErrorResponse.of(HttpStatus.UNAUTHORIZED, exceptionMassage +" : "+HttpStatus.UNAUTHORIZED.getReasonPhrase())
                        : ErrorResponse.of(HttpStatus.UNAUTHORIZED);
        return errorResponse;
    }
}
