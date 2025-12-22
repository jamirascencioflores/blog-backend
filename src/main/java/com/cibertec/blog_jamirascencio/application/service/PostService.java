package com.cibertec.blog_jamirascencio.application.service;

import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import com.cibertec.blog_jamirascencio.domain.model.Post;
import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.CategoriaRepositoryPort;
import com.cibertec.blog_jamirascencio.domain.port.PostRepositoryPort;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import com.cibertec.blog_jamirascencio.domain.port.in.ManagePostUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService implements ManagePostUseCase {

    private final PostRepositoryPort postRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;
    private final CategoriaRepositoryPort categoriaRepositoryPort;
    @Override
    public Post crearPost(Post post, Long categoriaId) { // <--- Recibimos ID
        String usernameActual = SecurityContextHolder.getContext().getAuthentication().getName();

        // 1. Buscar Usuario
        Usuario usuario = usuarioRepositoryPort.buscarPorUsername(usernameActual)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + usernameActual));
        post.setAutor(usuario);

        // 2. Buscar Categoría (NUEVO)
        Categoria categoria = categoriaRepositoryPort.buscarPorId(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada con ID: " + categoriaId));
        post.setCategoria(categoria); // <--- Asignamos la categoría

        // 3. Fecha
        if(post.getFechaCreacion() == null){
            post.setFechaCreacion(LocalDateTime.now());
        }

        return postRepositoryPort.guardar(post);
    }

    @Override
    public List<Post> listarPosts() {
        return postRepositoryPort.listarTodos();
    }

    @Override
    public Optional<Post> obtenerPostPorId(Long id) {
        return postRepositoryPort.buscarPorId(id);
    }

    @Override
    public boolean eliminarPost(Long id) {
        if(postRepositoryPort.buscarPorId(id).isPresent()){
            postRepositoryPort.eliminar(id);
            return true;
        }
        return false;
    }

    @Override
    public List<Post> listarPostsPorCategoria(Long categoriaId) {
        return postRepositoryPort.buscarPorCategoria(categoriaId);
    }

    public void eliminarPublicacion(Long id, String username, boolean isAdmin) {
        postRepositoryPort.eliminarPost(id, username, isAdmin);
    }
    // Dentro de tu PostService.java
    public Post actualizarCategoria(Long postId, Long categoriaId) {
        // Usamos los puertos que ya tienes inyectados (ej: postRepository o categoriaRepository)
        Post post = postRepositoryPort.buscarPorId(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        Categoria cat = categoriaRepositoryPort.buscarPorId(categoriaId)
                .orElseThrow(() -> new RuntimeException("Categoría no encontrada"));

        post.setCategoria(cat);
        return postRepositoryPort.guardar(post);
    }
}