package com.storeapi.store.repositories.specifications;

import com.storeapi.store.entities.Product;
import org.springframework.data.jpa.domain.Specification;

public class ProductSpec {
    public static Specification<Product> hasName(String name) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.like(root.get("name"), "%" + name + "%");
    }

    public static Specification<Product> hasPriceGreaterThanOrEqualTo(Double price) {
        return (root, query, criteriaBuilder) ->
                criteriaBuilder.greaterThanOrEqualTo(root.get("price"), price);
    }
}
