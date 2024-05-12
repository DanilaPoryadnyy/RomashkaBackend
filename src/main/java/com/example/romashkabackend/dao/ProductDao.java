package com.example.romashkabackend.dao;

import com.example.romashkabackend.model.Product;

import java.util.List;
import java.util.UUID;

public interface ProductDao {
    Product saveData(Product product);
    Product getDataById(UUID id);
    List<Product> getAllData();
    void deleteProductById(UUID id);
}
