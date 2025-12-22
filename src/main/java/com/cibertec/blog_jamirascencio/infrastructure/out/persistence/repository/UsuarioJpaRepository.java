package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository;

import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Long> {
    Optional<UsuarioEntity> findByUsername(String username);
}