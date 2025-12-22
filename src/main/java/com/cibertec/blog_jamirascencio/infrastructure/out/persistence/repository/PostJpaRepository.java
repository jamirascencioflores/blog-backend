package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository;

import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostJpaRepository extends JpaRepository<PostEntity, Long> {

    List<PostEntity> findByCategoriaId(Long categoriaId);
}