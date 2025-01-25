package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.VariantDTO;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.Variant;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class VariantMapper implements Mapper<Variant, VariantDTO> {
    @Override
    public VariantDTO mapTo(Variant variant) {
        return VariantDTO.builder()
                .productId(variant.getProduct().getId())
                .color(variant.getColor())
                .size(variant.getSize())
                .build();
    }

    @Override
    public Variant mapFrom(VariantDTO variantDTO) {
        return Variant.builder()
                .color(variantDTO.getColor())
                .product(Product.builder().id(variantDTO.getProductId()).build())
                .size(variantDTO.getSize())
                .build();
    }
}
