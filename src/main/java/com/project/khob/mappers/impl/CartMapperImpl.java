package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.CartDto;
import com.project.khob.domain.entities.Cart;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component
public class CartMapperImpl implements Mapper<Cart, CartDto> {
    private ModelMapper modelMapper;

    public CartMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }


    @Override
    public CartDto mapTo(Cart cart) {
        return modelMapper.map(cart,CartDto.class);
    }

    @Override
    public Cart mapFrom(CartDto cartDto) {
        return modelMapper.map(cartDto,Cart.class);
    }
}
