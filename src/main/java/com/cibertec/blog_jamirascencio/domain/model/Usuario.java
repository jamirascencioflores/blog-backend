package com.cibertec.blog_jamirascencio.domain.model;

import com.fasterxml.jackson.annotation.JsonProperty; // Cambia el import
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    private Long id;
    private String username;
    private String email;

    private String role;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;

    private Boolean esAdmin = false;
}