package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.entities.Product;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class ProductMapperImpl implements Mapper <Product, ProductDto> {

    private ModelMapper modelMapper;

    public ProductMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ProductDto mapTo(Product product) {
        return modelMapper.map(product,ProductDto.class);
    }

    @Override
    public Product mapFrom(ProductDto productDto) {
        return modelMapper.map(productDto,Product.class);
    }
}
