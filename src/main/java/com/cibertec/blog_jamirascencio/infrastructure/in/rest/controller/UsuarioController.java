package com.cibertec.blog_jamirascencio.infrastructure.in.rest.controller;

import com.cibertec.blog_jamirascencio.application.service.UsuarioService;
import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @GetMapping
    public ResponseEntity<List<Usuario>> listarUsuarios() {
        // 1. Obtener quién hace la petición
        String usernameActual = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario admin = usuarioService.buscarPorUsername(usernameActual);

        // 2. Validar que sea ADMIN
        if (!admin.getEsAdmin()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build(); // 403 Forbidden
        }

        // 3. Si es admin, mostramos la lista
        return ResponseEntity.ok(usuarioService.listarTodosLosUsuarios());
    }
}