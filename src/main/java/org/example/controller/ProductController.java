package org.example.controller;

import org.example.model.Product;
import org.example.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProductController {

    private final ProductRepository productRepository;

    @Autowired
    public ProductController(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @GetMapping("/products/{id}")
    public ResponseEntity<Product> findProductById(@PathVariable long id) {
        return ResponseEntity.ok(productRepository.getProductById(id));
    }

    @GetMapping("/products")
    public ResponseEntity<List<Product>> findAllProducts() {
        return ResponseEntity.ok(productRepository.getAllProducts());
    }

    @PostMapping("/products")
    public ResponseEntity<Void> createProduct(@RequestBody Product product) {
        productRepository.createProduct(product);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PutMapping("/products")
    public ResponseEntity<Void> updateProduct(@RequestBody Product product) {
        productRepository.updateProduct(product);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity<Void> removeProductById(@PathVariable long id) {
        productRepository.removeProductById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
