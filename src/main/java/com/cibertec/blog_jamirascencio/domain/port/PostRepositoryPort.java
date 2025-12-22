package com.cibertec.blog_jamirascencio.domain.port;

import com.cibertec.blog_jamirascencio.domain.model.Post;
import java.util.List;
import java.util.Optional;

public interface PostRepositoryPort {
    Post guardar(Post post);
    List<Post> listarTodos();
    Optional<Post> buscarPorId(Long id);
    void eliminar(Long id);

    // NUEVO: Buscar por categoría
    List<Post> buscarPorCategoria(Long categoriaId);
    void eliminarPost(Long postId, String currentUsername, boolean isAdmin);
}