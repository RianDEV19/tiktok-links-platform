package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import jakarta.validation.Valid;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;
    //Rota para Buscar todos os produtos
    @GetMapping
    public List<Product> productList(){
        return repository.findAll();
    }
    //Rota para SALVAR um produto novo
    @PostMapping
    public Product saveProduct(@Valid @RequestBody Product product){
        return repository.save(product);
    }

    @DeleteMapping
    public void deleteProduct(@PathVariable Long id){
        repository.deleteById(id);
    }

}
