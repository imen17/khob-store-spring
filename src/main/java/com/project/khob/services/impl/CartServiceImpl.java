package com.project.khob.services.impl;

import com.project.khob.domain.entities.Cart;
import com.project.khob.repositories.CartRepository;
import com.project.khob.services.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {
    private CartRepository cartRepository;

    public CartServiceImpl(CartRepository cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public Cart createCart(Cart cart) {
        return cartRepository.save(cart);
    }

    @Override
    public Page<Cart> findAll(Pageable pageable) {
        return cartRepository.findAll(pageable);
    }

    @Override
    public Optional<Cart> findOne(Long id) {
        return cartRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return cartRepository.existsById(id);
    }

    @Override
    public Cart partialUpdate(Long id, Cart cart) {
         cart.setCartId(id);

        return cartRepository.findById(id).map(existingCart -> {
            Optional.ofNullable(cart.getItems()).ifPresent(existingCart::setItems);
            return cartRepository.save(existingCart);
        }).orElseThrow(()->new RuntimeException("Cart doesn't exist"));    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);

    }
}
