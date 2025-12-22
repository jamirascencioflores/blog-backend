package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository;

import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.ComentarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComentarioJpaRepository extends JpaRepository<ComentarioEntity, Long> {

    // Spring Data JPA crea la query automáticamente con este nombre:
    List<ComentarioEntity> findByPostId(Long postId);
}