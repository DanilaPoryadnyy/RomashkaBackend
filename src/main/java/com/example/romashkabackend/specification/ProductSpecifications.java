package com.example.romashkabackend.specification;

import com.example.romashkabackend.model.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpecifications {

    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasPriceGreaterThan(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.greaterThan(root.get("price"), price);
    }

    public static Specification<Product> hasPriceLessThan(Double price) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.lessThan(root.get("price"), price);
    }

    public static Specification<Product> hasAvailability(Boolean available) {
        return (root, query, criteriaBuilder) -> criteriaBuilder.equal(root.get("available"), available);
    }

}
