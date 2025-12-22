package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper;

import com.cibertec.blog_jamirascencio.domain.model.Post;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.PostEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;
import org.springframework.context.annotation.Lazy;

@Component
public class PostMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Autowired
    private CategoriaMapper categoriaMapper;

    @Lazy
    @Autowired
    private ComentarioMapper comentarioMapper;

    public PostEntity toEntity(Post domain) {
        if (domain == null) return null;
        PostEntity entity = new PostEntity();
        entity.setId(domain.getId());
        entity.setTitulo(domain.getTitulo());
        entity.setContenido(domain.getContenido());
        entity.setFechaCreacion(domain.getFechaCreacion());
        entity.setAutor(usuarioMapper.toEntity(domain.getAutor()));
        entity.setCategoria(categoriaMapper.toEntity(domain.getCategoria()));
        return entity;
    }

    public Post toDomain(PostEntity entity) {
        if (entity == null) return null;
        Post post = new Post();
        post.setId(entity.getId());
        post.setTitulo(entity.getTitulo());
        post.setContenido(entity.getContenido());
        post.setFechaCreacion(entity.getFechaCreacion());

        post.setAutor(usuarioMapper.toDomain(entity.getAutor()));
        post.setCategoria(categoriaMapper.toDomain(entity.getCategoria()));

        // 2. CAMBIO AQUÍ: Ahora sí mapeamos los comentarios
        if (entity.getComentarios() != null) {
            post.setComentarios(entity.getComentarios().stream()
                    .map(comentarioMapper::toDomain)
                    .collect(Collectors.toList()));
        }

        return post;
    }
}