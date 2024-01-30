package com.project.khob.mappers.impl;

import com.project.khob.domain.dto.OrderDto;
import com.project.khob.domain.entities.Order;
import com.project.khob.mappers.Mapper;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component

public class OrderMapperImpl implements Mapper<Order, OrderDto> {
    private ModelMapper modelMapper;

    public OrderMapperImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public OrderDto mapTo(Order order) {
        return modelMapper.map(order,OrderDto.class);
    }

    @Override
    public Order mapFrom(OrderDto orderDto) {
        return modelMapper.map(orderDto,Order.class);
    }
}
