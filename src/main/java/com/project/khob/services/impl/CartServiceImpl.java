package com.project.khob.services.impl;

import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.User;
import com.project.khob.repositories.CartRepository;
import com.project.khob.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {
    private final CartRepository cartRepository;

    @Override
    public Cart findByUser(User user) {
        var cart = cartRepository.findByUserId(user.getUserId());
        if (cart.isEmpty()) {
            var newCart = Cart.builder().user(user).build();
            createCart(newCart);
            return newCart;
        }
        return cart.get();
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
            Optional.ofNullable(cart.getCartItems()).ifPresent(existingCart::setCartItems);
            return cartRepository.save(existingCart);
        }).orElseThrow(()->new RuntimeException("Cart doesn't exist"));    }

    @Override
    public void delete(Long id) {
        cartRepository.deleteById(id);

    }
}
