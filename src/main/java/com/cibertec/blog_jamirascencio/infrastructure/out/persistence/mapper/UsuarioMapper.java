package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.UsuarioEntity;
import org.springframework.stereotype.Component;

@Component
public class UsuarioMapper {

    public static Usuario toDomain(UsuarioEntity entity) {
        if (entity == null) return null;
        return new Usuario(
                entity.getId(),
                entity.getUsername(),
                entity.getEmail(),
                entity.getRole(),       // si usas este campo
                entity.getPassword(),
                entity.getEsAdmin()     // clave: esAdmin no puede ser null
        );
    }

    public static UsuarioEntity toEntity(Usuario usuario) {
        if (usuario == null) return null;
        UsuarioEntity entity = new UsuarioEntity();
        entity.setId(usuario.getId());
        entity.setUsername(usuario.getUsername());
        entity.setEmail(usuario.getEmail());
        entity.setPassword(usuario.getPassword());
        entity.setRole(usuario.getRole());
        entity.setEsAdmin(usuario.getEsAdmin()); // clave: mantener el valor
        return entity;
    }
}

