package com.project.khob.services;

import com.project.khob.domain.entities.Order;
import org.springframework.stereotype.Service;

@Service
public interface OrderService {
    Order createOrder(Order order);
}
