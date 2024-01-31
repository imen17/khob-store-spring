package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CartItemDto;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class ItemMapperImpl implements Mapper<CartItem, CartItemDto> {
    private final ModelMapper modelMapper;

    public ItemMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public CartItemDto mapTo(CartItem cartItem) {
        return modelMapper.map(cartItem, CartItemDto.class);
    }

    @Override
    public CartItem mapFrom(CartItemDto cartItemDto) {
        return modelMapper.map(cartItemDto, CartItem.class);
    }
}
