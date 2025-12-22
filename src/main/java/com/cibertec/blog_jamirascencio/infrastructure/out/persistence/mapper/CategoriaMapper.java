package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper;

import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.CategoriaEntity;
import org.springframework.stereotype.Component;

@Component
public class CategoriaMapper {

    // Convierte de Dominio a Entidad (Para guardar en BD)
    public CategoriaEntity toEntity(Categoria domain) {
        if (domain == null) return null;

        CategoriaEntity entity = new CategoriaEntity();
        entity.setId(domain.getId());
        entity.setNombre(domain.getNombre());
        entity.setDescripcion(domain.getDescripcion());

        return entity;
    }

    // Convierte de Entidad a Dominio (Para usar en la lógica)
    public Categoria toDomain(CategoriaEntity entity) {
        if (entity == null) return null;

        Categoria domain = new Categoria();
        domain.setId(entity.getId());
        domain.setNombre(entity.getNombre());
        domain.setDescripcion(entity.getDescripcion());

        return domain;
    }
}