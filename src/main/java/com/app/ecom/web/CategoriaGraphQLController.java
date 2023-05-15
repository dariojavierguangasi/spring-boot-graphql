package com.app.ecom.web;

import com.app.ecom.entities.Categoria;
import com.app.ecom.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;

import java.util.List;

@Controller
public class CategoriaGraphQLController {

    @Autowired
    private CategoriaRepository categoriaRepository;

    @QueryMapping
    private List<Categoria> findAllCategoria() {
        return categoriaRepository.findAll();
    }

    @QueryMapping
    private Categoria findCategoriaById(@Argument Long id) {
        return categoriaRepository.findById(id).orElseThrow(
                () -> new RuntimeException(String.format("Categoria %s no encontrado", id))
        );
    }
}
