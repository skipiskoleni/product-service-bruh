package org.example.repository;

import org.example.exception.ProductNotFoundException;
import org.example.model.Product;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductRepository {

    private final List<Product> products;

    public ProductRepository() {
        this.products = new ArrayList<>();
    }

    public void createProduct(Product product) {
        products.add(product);
    }

    public Product getProductById(long id) {
        return products.stream()
                .filter(product -> product.getId() == id)
                .findFirst().orElseThrow(
                        () -> new ProductNotFoundException(
                                String.format("Product with id %d was not found.", id)
                        )
                );
    }

    public void updateProduct(Product product) {
        Product found = getProductById(product.getId());
        found.setName(product.getName());
        found.setDescription(product.getDescription());
        found.setPrice(product.getPrice());
    }

    public void removeProductById(long id) {
        Product found = getProductById(id);
        products.remove(found);
    }

    public List<Product> getAllProducts() {
        return products;
    }
}
