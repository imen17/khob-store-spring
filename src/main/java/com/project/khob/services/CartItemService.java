package com.project.khob.services;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.CartItem;

import java.util.List;

public interface CartItemService {
        CartDTO getActiveItemsForCart(Cart cart);
        void saveAll(List<CartItem> cartItems);
}
