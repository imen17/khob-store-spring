package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CartItemsDTO;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.Variant;
import com.project.khob.mappers.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class CartItemsMapper implements Mapper<List<CartItem>, CartItemsDTO> {
    @Override
    public CartItemsDTO mapTo(List<CartItem> cartItemList) {
        return CartItemsDTO.builder()
                .productVariantIds(cartItemList.stream().map(
                        cartItem -> cartItem.getVariant().getId()).collect(Collectors.toList()))
                .build();
    }

    @Override
    public List<CartItem> mapFrom(CartItemsDTO cartItemsDTO) {
        return cartItemsDTO.getProductVariantIds().stream().map(
                variantId -> CartItem.builder()
                        .variant(Variant.builder()
                                .id(variantId)
                                .build())
                        .build())
                .collect(Collectors.toList());
    }
}
