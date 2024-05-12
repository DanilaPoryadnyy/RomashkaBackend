package com.example.romashkabackend.service.impl;

import com.example.romashkabackend.dao.ProductDao;
import com.example.romashkabackend.dto.ProductDTO;
import com.example.romashkabackend.model.Product;
import com.example.romashkabackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductDao productDao;


    @Override
    public Product createProduct(ProductDTO productDTO) {
        Product product = new Product();
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setAvailable(productDTO.getAvailable());
        return productDao.saveData(product);
    }

    @Override
    public Product updateProductById(UUID id, ProductDTO productDTO) {
        Product product = productDao.getDataById(id);
        product.setName(productDTO.getName());
        product.setDescription(productDTO.getDescription());
        product.setPrice(productDTO.getPrice());
        product.setAvailable(productDTO.getAvailable());
        return productDao.saveData(product);
    }

    @Override
    public Product getProductById(UUID id) {
        return productDao.getDataById(id);
    }

    @Override
    public List<Product> getAllProducts() {
        return productDao.getAllData();
    }

    @Override
    public void removeProductById(UUID id) {
        productDao.deleteProductById(id);
    }
}

