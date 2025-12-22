package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.adapter;

import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import com.cibertec.blog_jamirascencio.domain.port.CategoriaRepositoryPort;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.CategoriaEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper.CategoriaMapper;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.CategoriaJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class CategoriaPersistenceAdapter implements CategoriaRepositoryPort {

    private final CategoriaJpaRepository jpaRepository;
    private final CategoriaMapper mapper;

    @Override
    public List<Categoria> listarTodas() {
        return jpaRepository.findAll().stream()
                .map(mapper::toDomain)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Categoria> buscarPorId(Long id) {
        return jpaRepository.findById(id).map(mapper::toDomain);
    }

    @Override
    public Optional<Categoria> buscarPorNombre(String nombre) {
        return jpaRepository.findByNombre(nombre).map(mapper::toDomain);
    }

    @Override
    public Categoria guardar(Categoria categoria) {
        CategoriaEntity entity = mapper.toEntity(categoria);
        return mapper.toDomain(jpaRepository.save(entity));
    }

    @Override
    public void eliminar(Long id) {
        jpaRepository.deleteById(id);
    }
}