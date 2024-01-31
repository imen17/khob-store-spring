package com.project.khob.services;

import com.project.khob.domain.entities.CartItem;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartItemService {
    CartItem create(CartItem cartItem);
    Optional<CartItem> findById(Long id);
}
