package com.app.ecom;

import com.app.ecom.entities.Categoria;
import com.app.ecom.entities.Producto;
import com.app.ecom.repository.CategoriaRepository;
import com.app.ecom.repository.ProductoRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@SpringBootApplication
public class GraphqlSpringBootApplication {

    public static void main(String[] args) {
        SpringApplication.run(GraphqlSpringBootApplication.class, args);
    }


    // Esta clase nos sirve para guardar elementos en la base de datos
    @Bean
    CommandLineRunner commandLineRunner(CategoriaRepository categoriaRepository, ProductoRepository productoRepository) {

        Random random = new Random();

        return args -> {
            List.of("Computadoras", "Impresoras", "Smartphones").forEach(cat -> {
                Categoria categoria =  Categoria.builder().nombre(cat).build();
                categoriaRepository.save(categoria);
            });

            categoriaRepository.findAll().forEach(categoria -> {

                for (int i = 0; i < 10; i++) {
                    Producto producto = Producto.builder()
                            .id(UUID.randomUUID().toString())
                            .nombre("Producto " + categoria.getNombre() + " " + i )
                            .precio(BigDecimal.valueOf(100 + Math.random() * 100))
                            .cantidad(random.nextInt(50))
                            .categoria(categoria)
                            .build();
                    productoRepository.save(producto);
                }

            });

        };
    }

}
