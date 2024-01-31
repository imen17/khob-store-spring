package com.project.khob.services.impl;

import com.project.khob.domain.entities.ProductVariant;
import com.project.khob.repositories.ProductVariantRepository;
import com.project.khob.services.ProductVariantService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductVariantServiceImpl implements ProductVariantService {

    private final ProductVariantRepository productVariantRepository;
    @Override
    public ProductVariant create(ProductVariant productVariant) {
        return productVariantRepository.save(productVariant);
    }

    @Override
    public Optional<ProductVariant> findOne(Long id) {
        return productVariantRepository.findById(id);
    }
}
