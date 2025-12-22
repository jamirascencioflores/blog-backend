package com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "comentarios")
@Data
public class ComentarioEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 1000) // Limitamos longitud para evitar textos infinitos
    private String contenido;

    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    // Relación: Muchos comentarios pertenecen a un Usuario (Autor)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id", nullable = false)
    private UsuarioEntity autor;

    // Relación: Muchos comentarios pertenecen a un Post
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id", nullable = false)
    @JsonIgnore
    private PostEntity post;

}