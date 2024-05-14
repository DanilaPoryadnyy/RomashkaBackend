package com.example.romashkabackend.dao.impl;
import com.example.romashkabackend.dao.ProductDao;
import com.example.romashkabackend.exception.ProductNotFound;
import com.example.romashkabackend.model.Product;
import com.example.romashkabackend.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Component
public class ProductDaoImpl implements ProductDao {
    private ProductRepository productRepository;

    @Override
    public Product saveData(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getDataById(UUID id) {
        return productRepository.findById(id).orElseThrow(() -> new ProductNotFound("Product not found"));
    }
    

    @Override
    public List<Product> getAllData() {
        return productRepository.findAll();
    }

    @Override
    public void deleteProductById(UUID id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<Product> findAll(Specification<Product> spec) {
        return productRepository.findAll(spec);
    }

}
