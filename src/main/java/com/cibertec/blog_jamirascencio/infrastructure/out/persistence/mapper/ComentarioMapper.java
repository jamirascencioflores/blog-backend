package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper;

import com.cibertec.blog_jamirascencio.domain.model.Comentario;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.ComentarioEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

@Component
public class ComentarioMapper {

    @Autowired
    private UsuarioMapper usuarioMapper;

    @Lazy // Rompe el ciclo con PostMapper
    @Autowired
    private PostMapper postMapper;

    public Comentario toDomain(ComentarioEntity entity) {
        if (entity == null) return null;
        return new Comentario(
                entity.getId(),
                entity.getContenido(),
                entity.getFechaCreacion(),
                usuarioMapper.toDomain(entity.getAutor()),
                null
        );
    }

    public ComentarioEntity toEntity(Comentario domain) {
        if (domain == null) return null;
        ComentarioEntity entity = new ComentarioEntity();
        entity.setId(domain.getId());
        entity.setContenido(domain.getContenido());
        entity.setFechaCreacion(domain.getFechaCreacion());

        if(domain.getAutor() != null)
            entity.setAutor(usuarioMapper.toEntity(domain.getAutor()));

        if(domain.getPost() != null)
            entity.setPost(postMapper.toEntity(domain.getPost()));

        return entity;
    }
}