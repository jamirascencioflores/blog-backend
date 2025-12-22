package com.cibertec.blog_jamirascencio.application.service;

import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import com.cibertec.blog_jamirascencio.domain.port.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CategoriaService {

    private final CategoriaRepositoryPort categoriaRepositoryPort;

    public Categoria guardar(Categoria categoria) {
        return categoriaRepositoryPort.guardar(categoria);
    }
}