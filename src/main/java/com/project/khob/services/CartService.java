package com.project.khob.services;

import com.project.khob.domain.entities.Cart;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface CartService {
    Cart createCart(Cart cart);

    Page<Cart> findAll(Pageable pageable);

    Optional<Cart> findOne(Long id);

    boolean isExists(Long id);

    Cart partialUpdate(Long id, Cart cart);

    void delete(Long id);
}
