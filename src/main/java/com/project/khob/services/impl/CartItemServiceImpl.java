package com.project.khob.services.impl;

import com.project.khob.domain.entities.CartItem;
import com.project.khob.repositories.ItemRepository;
import com.project.khob.services.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {

    private final ItemRepository itemRepository;

    @Override
    public CartItem create(CartItem cartItem) {
        return itemRepository.save(cartItem);
    }

    @Override
    public Optional<CartItem> findById(Long id) {
        return itemRepository.findById(id);
    }
}
