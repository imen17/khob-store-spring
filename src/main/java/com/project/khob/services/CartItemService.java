package com.project.khob.services;

import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.CartItemKey;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartItemService {
    CartItem CreateItem(CartItem cartItem);
    Optional<CartItem> findById(CartItemKey id);
}
