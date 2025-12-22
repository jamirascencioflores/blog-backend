package com.cibertec.blog_jamirascencio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    private Long id;
    private String titulo;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private Usuario autor;
    private Categoria categoria;

    private List<Comentario> comentarios = new ArrayList<>();
}