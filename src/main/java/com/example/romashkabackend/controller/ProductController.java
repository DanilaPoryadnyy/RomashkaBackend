package com.example.romashkabackend.controller;

import com.example.romashkabackend.dto.ProductDTO;
import com.example.romashkabackend.model.Product;
import com.example.romashkabackend.service.ProductService;
import com.example.romashkabackend.specification.ProductSpecifications;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/product")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    public ResponseEntity<Product> createProduct(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.createProduct(productDTO), HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable UUID id) {
        return ResponseEntity.ok(productService.getProductById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable UUID id,@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(productService.updateProductById(id, productDTO), HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> removeProduct(@PathVariable UUID id) {
        productService.removeProductById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Product>> getAllProducts() {
        return ResponseEntity.ok(productService.getAllProducts());
    }

    @GetMapping("/search")
    public ResponseEntity<List<Product>> searchProducts(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Double minPrice,
            @RequestParam(required = false) Double maxPrice,
            @RequestParam(required = false) Boolean available
    ) {
        Specification<Product> spec = Specification.where(null);

        if (name != null && !name.isEmpty()) {
            spec = spec.and(ProductSpecifications.hasName(name));
        }
        if (minPrice != null) {
            spec = spec.and(ProductSpecifications.hasPriceGreaterThan(minPrice));
        }
        if (maxPrice != null) {
            spec = spec.and(ProductSpecifications.hasPriceLessThan(maxPrice));
        }
        if (available != null) {
            spec = spec.and(ProductSpecifications.hasAvailability(available));
        }

        List<Product> products = productService.findProductsWithSpecifications(spec);

        return ResponseEntity.ok(products);
    }
}
