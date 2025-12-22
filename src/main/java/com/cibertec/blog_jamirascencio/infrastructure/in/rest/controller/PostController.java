package com.cibertec.blog_jamirascencio.infrastructure.in.rest.controller;

import com.cibertec.blog_jamirascencio.application.service.PostService;
import com.cibertec.blog_jamirascencio.domain.model.Post;
import com.cibertec.blog_jamirascencio.domain.port.in.ManagePostUseCase;
import com.cibertec.blog_jamirascencio.application.service.ComentarioService; // <--- Importar
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/posts")
@RequiredArgsConstructor
public class PostController {

    private final ManagePostUseCase managePostUseCase;
    private final ComentarioService comentarioService;
    private final PostService postService;

    @Data
    static class CrearPostRequest {
        private String titulo;
        private String contenido;
        private Long categoriaId;
    }

    @PostMapping
    public ResponseEntity<Post> crearPost(@RequestBody CrearPostRequest request) {
        Post post = new Post();
        post.setTitulo(request.getTitulo());
        post.setContenido(request.getContenido());
        Post nuevoPost = managePostUseCase.crearPost(post, request.getCategoriaId());
        return new ResponseEntity<>(nuevoPost, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Post>> listarPosts(@RequestParam(required = false) Long categoriaId) {
        if (categoriaId != null) {
            return ResponseEntity.ok(managePostUseCase.listarPostsPorCategoria(categoriaId));
        }
        return ResponseEntity.ok(managePostUseCase.listarPosts());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Post> obtenerPost(@PathVariable Long id) {
        return managePostUseCase.obtenerPostPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPost(@PathVariable Long id) {
        if (managePostUseCase.eliminarPost(id)) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/comentarios/{id}")
    public ResponseEntity<?> eliminarComentario(@PathVariable Long id, Authentication auth) {
        String username = auth.getName();
        boolean isAdmin = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN") || a.getAuthority().equals("ADMIN"));
        comentarioService.eliminarComentario(id, username, isAdmin);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{postId}/categoria/{categoriaId}")
    public ResponseEntity<Post> asignarCategoria(
            @PathVariable Long postId,
            @PathVariable Long categoriaId) {
        return ResponseEntity.ok(postService.actualizarCategoria(postId, categoriaId));
    }
}