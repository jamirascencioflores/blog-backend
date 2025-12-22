package com.cibertec.blog_jamirascencio.infrastructure.in.rest.controller;

import com.cibertec.blog_jamirascencio.application.service.ComentarioService;
import com.cibertec.blog_jamirascencio.domain.model.Comentario;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comentarios")
@RequiredArgsConstructor
public class ComentarioController {

    private final ComentarioService comentarioService;

    // DTO: Solo pedimos Post ID y Contenido.
    // El usuario NO se envía, el backend lo deduce del Token.
    @Data
    static class CrearComentarioRequest {
        private Long postId;
        private String contenido;
    }

    @PostMapping
    public ResponseEntity<Comentario> crearComentario(@RequestBody CrearComentarioRequest request) {
        Comentario nuevoComentario = comentarioService.agregarComentario(
                request.getPostId(),
                request.getContenido()
        );
        return new ResponseEntity<>(nuevoComentario, HttpStatus.CREATED);
    }

    @GetMapping("/{postId}")
    public ResponseEntity<List<Comentario>> listarPorPost(@PathVariable Long postId) {
        return ResponseEntity.ok(comentarioService.listarPorPost(postId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();

        // Extraemos si es admin para enviárselo al Service
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ADMIN"));

        // Ahora pasamos los 3 parámetros: ID, Username e isAdmin
        comentarioService.eliminarComentario(id, username, isAdmin);

        return ResponseEntity.ok().build();
    }
}