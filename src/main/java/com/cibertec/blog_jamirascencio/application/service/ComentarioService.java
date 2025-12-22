package com.cibertec.blog_jamirascencio.application.service;

import com.cibertec.blog_jamirascencio.domain.model.Comentario;
import com.cibertec.blog_jamirascencio.domain.model.Post;
import com.cibertec.blog_jamirascencio.domain.model.Usuario;
import com.cibertec.blog_jamirascencio.domain.port.ComentarioRepositoryPort;
import com.cibertec.blog_jamirascencio.domain.port.PostRepositoryPort;
import com.cibertec.blog_jamirascencio.domain.port.UsuarioRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ComentarioService {

    private final ComentarioRepositoryPort comentarioRepositoryPort;
    private final PostRepositoryPort postRepositoryPort;
    private final UsuarioRepositoryPort usuarioRepositoryPort;

    // Método principal: Crear Comentario
    public Comentario agregarComentario(Long postId, String contenido) {
        // 1. Obtener quién está logueado (desde el Token)
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Usuario autor = usuarioRepositoryPort.buscarPorUsername(username)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado: " + username));

        // 2. Obtener el Post al que queremos comentar
        Post post = postRepositoryPort.buscarPorId(postId)
                .orElseThrow(() -> new RuntimeException("Post no encontrado con ID: " + postId));

        // 3. Crear el objeto Comentario
        Comentario comentario = new Comentario();
        comentario.setContenido(contenido);
        comentario.setFechaCreacion(LocalDateTime.now());
        comentario.setAutor(autor); // Asignamos el autor
        comentario.setPost(post);   // Asignamos el post

        // 4. Guardar en BD
        return comentarioRepositoryPort.guardar(comentario);
    }

    // Método para listar comentarios de un post específico
    public List<Comentario> listarPorPost(Long postId) {
        return comentarioRepositoryPort.buscarPorPostId(postId);
    }
    public void eliminarComentario(Long id, String usernameSolicitante, boolean isAdmin) {
        comentarioRepositoryPort.eliminarComentario(id, usernameSolicitante, isAdmin);
    }
}