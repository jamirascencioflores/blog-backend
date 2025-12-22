package com.cibertec.blog_jamirascencio.infrastructure.in.rest.controller;

import com.cibertec.blog_jamirascencio.application.service.AuthService;
import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200") // Permite que Angular se conecte
@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody Usuario usuario) {
        authService.registrar(usuario);
        return ResponseEntity.ok(Map.of("message", "Usuario registrado con éxito"));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> request) {

        Map<String, String> loginResponse = authService.login(
                request.get("username"),
                request.get("password")
        );

        return ResponseEntity.ok(loginResponse);
    }



    @GetMapping("/ping")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }
}