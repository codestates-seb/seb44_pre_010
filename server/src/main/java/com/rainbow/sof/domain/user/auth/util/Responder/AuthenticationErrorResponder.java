package com.rainbow.sof.domain.user.auth.util.Responder;

import com.google.gson.Gson;
import com.rainbow.sof.global.common.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthenticationErrorResponder {
    public static void sendErrorResponse(HttpServletResponse response, HttpStatus status) throws IOException, IOException {
        Gson gson = new Gson();
        ErrorResponse errorResponse = ErrorResponse.of(status);
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setStatus(status.value());
        response.getWriter().write(gson.toJson(errorResponse, ErrorResponse.class));
    }
}
