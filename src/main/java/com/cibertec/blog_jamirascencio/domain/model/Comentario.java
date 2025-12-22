package com.cibertec.blog_jamirascencio.domain.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comentario {
    private Long id;
    private String contenido;
    private LocalDateTime fechaCreacion;
    private Usuario autor;

    @JsonIgnore
    private Post post;
}