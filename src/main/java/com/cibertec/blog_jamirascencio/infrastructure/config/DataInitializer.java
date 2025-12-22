package com.cibertec.blog_jamirascencio.infrastructure.config;

import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.CategoriaEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.entity.UsuarioEntity;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.CategoriaJpaRepository;
import com.cibertec.blog_jamirascencio.infrastructure.out.persistence.repository.UsuarioJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final CategoriaJpaRepository categoriaRepository;
    private final UsuarioJpaRepository usuarioRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // 1. Crear Categorías por defecto si no existen
        if (categoriaRepository.count() == 0) {
            CategoriaEntity catJava = new CategoriaEntity();
            catJava.setNombre("Java - Backend");
            catJava.setDescripcion("Temas relacionados a Spring Boot y Java");

            CategoriaEntity catAngular = new CategoriaEntity();
            catAngular.setNombre("Angular - Frontend");
            catAngular.setDescripcion("Temas de diseño y arquitectura SPA");

            categoriaRepository.saveAll(Arrays.asList(catJava, catAngular));
            System.out.println("✅ Categorías iniciales creadas.");
        }

        // 2. Crear Usuario Admin si no existe
        if (usuarioRepository.findByUsername("admin").isPresent()) {
            UsuarioEntity admin = usuarioRepository.findByUsername("admin").get();
            admin.setEsAdmin(true);
            usuarioRepository.save(admin);
        }

    }
}