package com.project.khob.services;

import com.project.khob.domain.entities.ProductVariant;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductVariantService {
    ProductVariant create(ProductVariant productVariant);
    Optional<ProductVariant> findOne(Long id);
}
