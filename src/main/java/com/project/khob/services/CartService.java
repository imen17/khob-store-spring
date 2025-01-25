package com.project.khob.services;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.dto.CartItemRequestDTO;
import com.project.khob.domain.dto.CartItemsDTO;
import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.User;

public interface CartService {
    CartDTO getActiveCartItemsForUser(User user);

    void addCartItemsToCart(CartItemsDTO cartItemsDTO, User user);

    void removeCartItemsFromCart(CartItemRequestDTO cartItemRequestDTO, User user);

    void clearCart(CartItemsDTO cartItemsDTO);

    Cart getNewestCartForUser(User user);
}
