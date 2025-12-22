package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository;

import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface CategoriaJpaRepository extends JpaRepository<CategoriaEntity, Long> {
    Optional<CategoriaEntity> findByNombre(String nombre);
}