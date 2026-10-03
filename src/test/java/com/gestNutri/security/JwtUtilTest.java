package com.gestNutri.security;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.gestNutri.exception.GlobalExceptionHandler;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.userdetails.User;

class JwtUtilTest {
    private final JwtUtil jwtUtil = new JwtUtil("test-secret-for-jwt-context-only-0123456789");

    @Test
    void generatedTokenIncludesGrantedRole() {
        var user = User.withUsername("admin1@gestnutri.local")
                .password("unused")
                .authorities("ROLE_ADMINISTRATEUR")
                .build();

        String token = jwtUtil.genererToken(user);
        String payload = new String(Base64.getUrlDecoder().decode(token.split("\\.")[1]), StandardCharsets.UTF_8);

        assertTrue(payload.contains("\"role\":\"ROLE_ADMINISTRATEUR\""));
    }

    @Test
    void badCredentialsReturnUnauthorized() {
        var response = new GlobalExceptionHandler().handleAuthentication(
                new BadCredentialsException("invalid credentials"));

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }
}