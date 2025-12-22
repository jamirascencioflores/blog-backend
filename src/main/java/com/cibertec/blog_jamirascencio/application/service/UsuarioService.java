package com.cibertec.blog_jamirascencio.application.service;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    public List<Usuario> listarTodosLosUsuarios() {
        return usuarioRepositoryPort.listarTodos();
    }

    public Usuario buscarPorUsername(String username) {
        return usuarioRepositoryPort.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
    }
}