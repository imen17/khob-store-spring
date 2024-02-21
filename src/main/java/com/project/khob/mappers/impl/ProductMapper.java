package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.ProductDTO;
import com.project.khob.domain.entities.Photo;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.SubCategory;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.Optional;
import java.util.stream.Collectors;

@Component

public class ProductMapper implements Mapper<Product, ProductDTO> {

    @Override
    public ProductDTO mapTo(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getDescription(),
                product.getPrice(),
                product.getSubCategory().getId(),
                product.getSubCategory().getName(),
                Optional.ofNullable(product.getPhotos())
                        .orElseGet(Collections::emptyList)
                        .stream().map(Photo::getUrl).collect(Collectors.toList())
        );
    }

    @Override
    public Product mapFrom(ProductDTO productDTO) {
        return Product.builder()
                .name(productDTO.getName())
                .description(productDTO.getDescription())
                .price(productDTO.getPrice())
                .subCategory(SubCategory.builder()
                        .id(productDTO.getCategoryId())
                        .build())
                .photos(Optional.ofNullable(productDTO.getPhotoUrls())
                        .orElseGet(Collections::emptyList)
                        .stream().map(url -> Photo.builder().url(url).build()).collect(Collectors.toList()))
                .build();
    }
}
