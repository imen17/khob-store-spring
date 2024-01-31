package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.ItemDto;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class ItemMapperImpl implements Mapper<CartItem, ItemDto> {
    private final ModelMapper modelMapper;

    public ItemMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public ItemDto mapTo(CartItem cartItem) {
        return modelMapper.map(cartItem, ItemDto.class);
    }

    @Override
    public CartItem mapFrom(ItemDto itemDto) {
        return modelMapper.map(itemDto, CartItem.class);
    }
}
