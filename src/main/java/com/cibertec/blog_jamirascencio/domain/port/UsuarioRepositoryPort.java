package com.cibertec.blog_jamirascencio.domain.port;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import java.util.Optional;
import java.util.List;

public interface UsuarioRepositoryPort {
    Usuario guardar(Usuario usuario);
    Optional<Usuario> buscarPorId(Long id);
    Optional<Usuario> buscarPorUsername(String username);
    List<Usuario> listarTodos();
}