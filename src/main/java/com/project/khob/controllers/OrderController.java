package com.project.khob.controllers;

import com.project.khob.domain.dto.OrderDto;
import com.project.khob.domain.entities.Order;
import com.project.khob.services.OrderService;
import com.project.khob.mappers.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController {
    private OrderService orderService;

    private Mapper<Order, OrderDto> orderMapper;

    public OrderController(OrderService orderService, Mapper<Order, OrderDto> orderMapper) {
        this.orderService = orderService;
        this.orderMapper = orderMapper;
    }

    @PostMapping(path = "/orders/new")
    public ResponseEntity<OrderDto> createProduct(@RequestBody OrderDto orderDto) {
        Order order = orderMapper.mapFrom(orderDto);
        Order savedOrder = orderService.createOrder(order);
        return new ResponseEntity<>(orderMapper.mapTo(savedOrder), HttpStatus.CREATED);
    }

}