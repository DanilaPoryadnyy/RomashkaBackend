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
import java.util.Objects;
import java.util.UUID;
import java.util.function.Supplier;
import java.util.stream.Stream;

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
            @RequestParam(required = false) Boolean available,
            @RequestParam(required = false) String sortBy
    ) {
        List<Specification<Product>> filters = Stream.of(
                        buildNameSpecification(name),
                        buildMinPriceSpecification(minPrice),
                        buildMaxPriceSpecification(maxPrice),
                        buildAvailabilitySpecification(available)
                )
                .filter(Objects::nonNull)
                .toList();

        Specification<Product> combinedSpec = filters.stream().reduce(Specification::and).orElse(null);

        if (sortBy != null && !sortBy.isEmpty()) {
            switch (sortBy.toLowerCase()) {
                case "name":
                    combinedSpec = addSortSpecification(combinedSpec, ProductSpecifications::sortByName);
                    break;
                case "price":
                    combinedSpec = addSortSpecification(combinedSpec, ProductSpecifications::sortByPrice);
                    break;
                default:
                    break;
            }
        }

        List<Product> products = productService.findProductsWithSpecifications(combinedSpec);

        return ResponseEntity.ok(products);
    }

    private Specification<Product> addSortSpecification(Specification<Product> spec, Supplier<Specification<Product>> sortSupplier) {
        return spec != null ? spec.and(sortSupplier.get()) : sortSupplier.get();
    }

    private Specification<Product> buildNameSpecification(String name) {
        return name != null && !name.isEmpty() ? ProductSpecifications.hasName(name) : null;
    }

    private Specification<Product> buildMinPriceSpecification(Double minPrice) {
        return minPrice != null ? ProductSpecifications.hasPriceGreaterThan(minPrice) : null;
    }

    private Specification<Product> buildMaxPriceSpecification(Double maxPrice) {
        return maxPrice != null ? ProductSpecifications.hasPriceLessThan(maxPrice) : null;
    }

    private Specification<Product> buildAvailabilitySpecification(Boolean available) {
        return available != null ? ProductSpecifications.hasAvailability(available) : null;
    }
}
