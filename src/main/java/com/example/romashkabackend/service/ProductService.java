package com.example.romashkabackend.service;

import com.example.romashkabackend.exception.ProductNotFound;
import com.example.romashkabackend.model.Product;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductService {
    private final Map<UUID, Product> products = new HashMap<>();

    public ResponseEntity<Product> createProduct(Product product) {
        UUID productId = UUID.randomUUID();
        products.put(productId, product);
        URI location = URI.create("/product/" + productId);
        return ResponseEntity.created(location).build();
    }

    public ResponseEntity<Product> getProduct(UUID id) {
        Product product = products.get(id);
        if (products.get(id) == null) {
            throw new ProductNotFound("Продукт с id " + id + " не найден");
        }
        return ResponseEntity.ok(product);
    }


    public ResponseEntity<Product> updateProduct(UUID id, Product product) {
        products.put(id, product);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Product> removeProduct(UUID id) {
        products.remove(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<Map> getAllProducts() {
        return ResponseEntity.ok(products);
    }
}

