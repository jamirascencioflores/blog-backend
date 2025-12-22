package com.cibertec.blog_jamirascencio.infrastructure.in.rest.controller;

import com.cibertec.blog_jamirascencio.application.service.CategoriaService;
import com.cibertec.blog_jamirascencio.domain.model.Categoria;
import com.cibertec.blog_jamirascencio.domain.port.CategoriaRepositoryPort;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/categorias")
@RequiredArgsConstructor
public class CategoriaController {

    private final CategoriaRepositoryPort categoriaRepositoryPort;
    private final CategoriaService categoriaService;

    @GetMapping
    public List<Categoria> listarTodas() {
        return categoriaRepositoryPort.listarTodas();
    }

    @PostMapping
    public ResponseEntity<Categoria> crear(@RequestBody Categoria categoria) {
        return ResponseEntity.ok(categoriaService.guardar(categoria));
    }
}