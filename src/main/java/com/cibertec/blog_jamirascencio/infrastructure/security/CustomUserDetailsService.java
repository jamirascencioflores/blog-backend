package com.cibertec.blog_jamirascencio.infrastructure.security;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UsuarioRepositoryPort usuarioRepositoryPort;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Usuario usuario = usuarioRepositoryPort.buscarPorUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String rolTexto = (usuario.getEsAdmin() != null && usuario.getEsAdmin())
                ? "ROLE_ADMIN"
                : "ROLE_USER";

        return User.builder()
                .username(usuario.getUsername())
                .password(usuario.getPassword())
                .authorities(rolTexto) // Spring Security usará esto internamente
                .build();
    }
}
