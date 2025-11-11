package com.fiap.gs.api.auth;

import com.fiap.gs.api.security.JwtUtil;
import jakarta.validation.constraints.NotBlank;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;

    public AuthController(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    public record LoginRequest(@NotBlank String username, @NotBlank String password) {}
    public record LoginResponse(String token, long expiresInMs) {}

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest req) {
        // DEMO: autenticação simples (troque por busca em DB se quiser)
        boolean ok = ("admin".equals(req.username()) && "admin123".equals(req.password()))
                  || ("user".equals(req.username()) && "user123".equals(req.password()));
        if (!ok) {
            return ResponseEntity.status(401).body(Map.of("error", "invalid_credentials"));
        }
        String token = jwtUtil.generateToken(req.username());
        return ResponseEntity.ok(new LoginResponse(token, 3600000));
    }
}
