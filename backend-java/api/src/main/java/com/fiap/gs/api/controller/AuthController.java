package com.fiap.gs.api.controller;

import com.fiap.gs.api.security.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwt;

    public AuthController(JwtUtil jwt) {
        this.jwt = jwt;
    }

    // DEMO: autenticação "fake" (admin/admin).
    // Para GS é suficiente demonstrar segurança; em produção, valide com BD.
    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest req) {
        if ("admin".equals(req.username()) && "admin".equals(req.password())) {
            String token = jwt.generateToken(req.username());
            return ResponseEntity.ok(new LoginResponse(token, "Bearer"));
        }
        return ResponseEntity.status(401).build();
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record LoginResponse(String accessToken, String tokenType) {}
}

