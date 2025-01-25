package com.project.khob.services.impl;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.impl.CartMapper;
import com.project.khob.repositories.CartItemRepository;
import com.project.khob.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final CartItemRepository cartItemRepository;
    private final CartMapper cartMapper;
    @Override
    public CartDTO getActiveItemsForCart(Cart cart) {
        List<CartItem> cartItems = cartItemRepository.getActiveItemsByCartId(cart.getId())
                .stream().filter(cartItem -> cartItem.getDateRemoved() == null)
                .collect(Collectors.toList());
        return cartMapper.mapTo(cartItems);
    }

    @Override
    public void saveAll(List<CartItem> cartItems) {
        cartItemRepository.saveAll(cartItems);
    }
}
