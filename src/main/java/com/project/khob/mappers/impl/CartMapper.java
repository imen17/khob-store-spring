package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.dto.CartItemDTO;
import com.project.khob.domain.dto.PhotoDTO;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.Mapper;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor

public class CartMapper implements Mapper<List<CartItem>, CartDTO> {

    private final CartItemMapper cartItemMapper;
    @Override
    public CartDTO mapTo(List<CartItem> cartItemList) {
        List<CartItemDTO> cartItemDTOS = cartItemList.stream().map(cartItemMapper::mapTo).toList();
        return CartDTO.builder()
                .cartItems(cartItemDTOS)
                .build();
    }

    @Override
    public List<CartItem> mapFrom(CartDTO cartDTO) {
        return cartDTO.getCartItems().stream().map(cartItemMapper::mapFrom).toList();
    }
}
