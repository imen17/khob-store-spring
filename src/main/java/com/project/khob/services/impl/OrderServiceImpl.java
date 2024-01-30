package com.project.khob.services.impl;

import com.project.khob.domain.entities.Order;
import com.project.khob.domain.entities.Product;
import com.project.khob.repositories.OrderRepository;
import com.project.khob.repositories.ProductRepository;
import com.project.khob.services.OrderService;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }
}
