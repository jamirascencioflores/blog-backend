package com.cibertec.blog_jamirascencio.application.service;

import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import com.cibertec.blog_jamirascencio.infrastructure.security.CustomUserDetailsService;
import com.cibertec.blog_jamirascencio.infrastructure.security.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UsuarioRepositoryPort usuarioRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService userDetailsService;



    public String registrar(Usuario usuario) {
        if (usuario.getEsAdmin() == null) {
            usuario.setEsAdmin(false);
        }
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        usuarioRepository.guardar(usuario);
        return "Usuario registrado con éxito";
    }

    public Map<String, String> login(String username, String password) {
        Usuario usuario = usuarioRepository.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado"));

        if (!passwordEncoder.matches(password, usuario.getPassword())) {
            throw new RuntimeException("Credenciales inválidas");
        }

        // Creamos UserDetails con el rol correcto
        UserDetails userDetails = userDetailsService.loadUserByUsername(username);

        String token = jwtService.generateToken(userDetails);

        String rol = userDetails.getAuthorities().stream()
                .map(a -> a.getAuthority())
                .findFirst()
                .orElse("ROLE_USER");

        return Map.of(
                "token", token,
                "role", rol.replace("ROLE_", "") // ADMIN o USER
        );

    }
}
