package com.rainbow.sof.domain.user.auth.handler.loginhandle;

import com.google.gson.Gson;
import com.rainbow.sof.global.common.ErrorResponse;
import com.rainbow.sof.global.error.ExceptionCode;
import io.jsonwebtoken.security.SignatureException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Slf4j
public class UserAuthenticationFailureHandler implements AuthenticationFailureHandler {
    private static final String IssueAtAuthenticationProvider =  HttpStatus.UNAUTHORIZED.getReasonPhrase();

    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
                                        AuthenticationException exception) throws IOException  {
        log.error("# Authentication failed: {}", exception.getMessage());
        log.error("# Authentication failed Exception ClassName: {} ", exception.getClass().getName());
        Gson gson = new Gson();
        sendErrorResponse(response, gson,exception);

    }

    private static void sendErrorResponse(HttpServletResponse response, Gson gson, AuthenticationException exception) throws IOException {

        String exceptionMassage =
                exception.getClass().getName().equals(
                        BadCredentialsException.class.getName()
                ) ? IssueAtAuthenticationProvider :
                        exception.getMessage();

        ErrorResponse errorResponse = getErrorResponse(exceptionMassage);

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(HttpStatus.UNAUTHORIZED.value());

        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }

    private static ErrorResponse getErrorResponse(String exceptionMassage) {
        if (exceptionMassage == null || exceptionMassage.equals(IssueAtAuthenticationProvider)){
            return ErrorResponse.of(HttpStatus.UNAUTHORIZED);
        }
        return ErrorResponse.of(HttpStatus.UNAUTHORIZED, exceptionMassage +" : "+HttpStatus.UNAUTHORIZED.getReasonPhrase());
    }
}