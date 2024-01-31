package com.project.khob.controllers;

import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.dto.ProductVariantDto;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.ProductVariant;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.ProductService;
import com.project.khob.services.ProductVariantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
public class ProductVariantController {

    private final ProductService productService;
    private final ProductVariantService productVariantService;
    private final Mapper<ProductVariant,ProductVariantDto> productVariantMapper;

    @PostMapping("/admin/products/{productId}/variants/")
    public ResponseEntity<ProductVariantDto> createProductVariant(@PathVariable("productId") Long productId, @RequestBody ProductVariantDto productVariantDto){
        var product = productService.findOne(productId);
        if (product.isEmpty()) {
            return ResponseEntity.badRequest().build();
        }
        var productVariant = productVariantMapper.mapFrom(productVariantDto);
        productVariant.setProduct(product.get());
        var savedProductVariant = productVariantService.create(productVariant);
        return new ResponseEntity<>(productVariantMapper.mapTo(savedProductVariant), HttpStatus.CREATED);
    }


}
