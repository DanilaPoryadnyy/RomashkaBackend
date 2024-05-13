package com.example.romashkabackend.service;

import com.example.romashkabackend.dto.ProductDTO;
import com.example.romashkabackend.model.Product;
import org.springframework.data.jpa.domain.Specification;

import java.util.List;
import java.util.UUID;

public interface ProductService {
    Product createProduct(ProductDTO productDTO);
    Product updateProductById(UUID id, ProductDTO productDTO);
    Product getProductById(UUID id);
    List<Product> getAllProducts();
    void removeProductById(UUID id);
    List<Product> findProductsWithSpecifications(Specification<Product> spec);
}
