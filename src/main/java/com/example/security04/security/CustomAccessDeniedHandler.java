package com.example.security04.security;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import java.io.IOException;

class CustomAccessDeniedHandler implements AccessDeniedHandler {

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException exc) throws IOException {
        //response.addHeader("access_denied_reason", "not_authorized");
        //response.getOutputStream().println("{\"mensaje\":\"No tiene permisos para realizar esta acción.\"}");

        response.addHeader("access_denied_reason", "not_authorized");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        response.getOutputStream().println("Not authorized");
    }
}
