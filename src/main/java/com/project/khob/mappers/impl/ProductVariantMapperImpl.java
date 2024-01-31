package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.dto.ProductVariantDto;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.ProductVariant;
import com.project.khob.mappers.Mapper;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class ProductVariantMapperImpl implements Mapper <ProductVariant, ProductVariantDto> {

    private final ModelMapper modelMapper;

    @Override
    public ProductVariantDto mapTo(ProductVariant productVariant) {
        return modelMapper.map(productVariant,ProductVariantDto.class);
    }

    @Override
    public ProductVariant mapFrom(ProductVariantDto productVariantDto) {
        return modelMapper.map(productVariantDto,ProductVariant.class);
    }
}
