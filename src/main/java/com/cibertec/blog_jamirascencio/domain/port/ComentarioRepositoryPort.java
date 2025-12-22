package com.cibertec.blog_jamirascencio.domain.port;

import com.cibertec.blog_jamirascencio.domain.model.Comentario;
import java.util.List;
import java.util.Optional;

public interface ComentarioRepositoryPort {
    Comentario guardar(Comentario comentario);
    Optional<Comentario> buscarPorId(Long id);

    // Necesario para listar los comentarios de un blog específico
    List<Comentario> buscarPorPostId(Long postId);

    void eliminar(Long id);

    void eliminarComentario(Long comentarioId, String currentUsername, boolean isAdmin);
}