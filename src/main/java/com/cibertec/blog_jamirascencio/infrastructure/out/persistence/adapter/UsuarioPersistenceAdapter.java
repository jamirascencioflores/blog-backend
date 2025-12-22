package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.adapter;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.UsuarioEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.mapper.UsuarioMapper;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
@RequiredArgsConstructor
public class UsuarioPersistenceAdapter implements UsuarioRepositoryPort {

    private final UsuarioJpaRepository jpaRepository;

    @Override
    public Usuario guardar(Usuario usuario) {
        UsuarioEntity entity = UsuarioMapper.toEntity(usuario);
        UsuarioEntity saved = jpaRepository.save(entity);
        return UsuarioMapper.toDomain(saved);
    }

    @Override
    public Optional<Usuario> buscarPorId(Long id) {
        return jpaRepository.findById(id)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public Optional<Usuario> buscarPorUsername(String username) {
        return jpaRepository.findByUsername(username)
                .map(UsuarioMapper::toDomain);
    }

    @Override
    public List<Usuario> listarTodos() {
        return jpaRepository.findAll()
                .stream()
                .map(UsuarioMapper::toDomain)
                .toList();
    }
}
