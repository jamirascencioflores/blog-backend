package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.adapter;

import com.cibertec.blog_jamirascencio.domain.model.Comentario;
import com.cibertec.blog_jamirascencio.domain.port.ComentarioRepositoryPort;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.ComentarioEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper.ComentarioMapper;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.ComentarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class ComentarioPersistenceAdapter implements ComentarioRepositoryPort {

    private final ComentarioJpaRepository comentarioJpaRepository;
    private final ComentarioMapper comentarioMapper;

    @Override
    public Comentario guardar(Comentario comentario) {
        ComentarioEntity entity = comentarioMapper.toEntity(comentario);
        ComentarioEntity guardado = comentarioJpaRepository.save(entity);
        return comentarioMapper.toDomain(guardado);
    }

    @Override
    public Optional<Comentario> buscarPorId(Long id) {
        return comentarioJpaRepository.findById(id)
                .map(comentarioMapper::toDomain);
    }

    @Override
    public List<Comentario> buscarPorPostId(Long postId) {
        // Usamos el método mágico del JPA Repository y convertimos la lista
        List<ComentarioEntity> entidades = comentarioJpaRepository.findByPostId(postId);
        return entidades.stream()
                .map(comentarioMapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        comentarioJpaRepository.deleteById(id);
    }

    @Override
    public void eliminarComentario(Long comentarioId, String currentUsername, boolean isAdmin) {
        // 1. Buscar el comentario
        ComentarioEntity comentario = comentarioJpaRepository.findById(comentarioId)
                .orElseThrow(() -> new RuntimeException("Comentario no encontrado"));

        // 2. Obtener dueños
        String autorComentario = comentario.getAutor().getUsername();
        String autorDelPost = comentario.getPost().getAutor().getUsername();

        // 3. Validar Reglas
        if (isAdmin ||
                autorComentario.equals(currentUsername) ||
                autorDelPost.equals(currentUsername)) {

            comentarioJpaRepository.delete(comentario);
        } else {
            throw new AccessDeniedException("No tienes permiso para borrar este comentario");
        }
    }
}