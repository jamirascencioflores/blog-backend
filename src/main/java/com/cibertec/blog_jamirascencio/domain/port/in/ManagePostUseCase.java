package com.cibertec.blog_jamirascencio.domain.port.in;

import com.cibertec.blog_jamirascencio.domain.model.Post;
import java.util.List;
import java.util.Optional;

public interface ManagePostUseCase {
    Post crearPost(Post post, Long categoriaId);

    List<Post> listarPosts();
    Optional<Post> obtenerPostPorId(Long id);
    boolean eliminarPost(Long id);
    List<Post> listarPostsPorCategoria(Long categoriaId);
}