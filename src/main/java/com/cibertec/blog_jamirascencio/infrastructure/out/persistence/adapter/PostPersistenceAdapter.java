package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.adapter;

import com.cibertec.blog_jamirascencio.domain.model.Post;
import com.cibertec.blog_jamirascencio.domain.port.PostRepositoryPort;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.PostEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper.PostMapper;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.PostJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class PostPersistenceAdapter implements PostRepositoryPort {

    private final PostJpaRepository jpaRepository;
    private final PostMapper mapper;

    @Override
    public Post guardar(Post post) {
        PostEntity entity = mapper.toEntity(post);
        PostEntity savedEntity = jpaRepository.save(entity);
        return mapper.toDomain(savedEntity);
    }

    @Override
    public Optional<Post> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public List<Post> listarTodos() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public List<Post> buscarPorCategoria(Long categoriaId) {
        List<PostEntity> entidades = jpaRepository.findByCategoriaId(categoriaId);
        return entidades.stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    public void eliminarPost(Long postId, String currentUsername, boolean isAdmin) {
        // CORREGIDO: Usamos jpaRepository
        PostEntity post = jpaRepository.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado"));

        // Verificamos si es admin o si el username del autor coincide
        if (isAdmin || (post.getAutor() != null && post.getAutor().getUsername().equals(currentUsername))) {
            jpaRepository.delete(post);
        } else {
            throw new AccessDeniedException("No tienes permiso para eliminar esta publicación");
        }
    }
}