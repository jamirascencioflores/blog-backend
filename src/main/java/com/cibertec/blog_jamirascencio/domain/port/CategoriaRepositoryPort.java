package com.cibertec.blog_jamirascencio.domain.port;

import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import java.util.List;
import java.util.Optional;

public interface CategoriaRepositoryPort {
    List<Categoria> listarTodas();
    Optional<Categoria> buscarPorId(Long id);
    Optional<Categoria> buscarPorNombre(String nombre);
    Categoria guardar(Categoria categoria);
    void eliminar(Long id);
}