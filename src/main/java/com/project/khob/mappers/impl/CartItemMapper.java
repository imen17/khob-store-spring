package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CartItemDTO;
import com.project.khob.domain.dto.VariantDTO;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.Product;
import com.project.khob.domain.entities.Variant;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

@Component
public class CartItemMapper implements Mapper<CartItem, CartItemDTO> {
    @Override
    public CartItemDTO mapTo(CartItem cartItem) {
        Variant variant = cartItem.getVariant();
        Product product = variant.getProduct();
        return CartItemDTO.builder()
                .name(product.getName())
                .price(product.getPrice())
                .productVariantId(variant.getId())
                .build();
    }

    @Override
    public CartItem mapFrom(CartItemDTO cartItemDTO) {
        return CartItem.builder()
                .variant(Variant.builder().id(cartItemDTO.getProductVariantId()).build())
                .build();
    }
}
