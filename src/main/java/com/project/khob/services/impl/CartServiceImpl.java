package com.project.khob.services.impl;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.dto.CartItemRequestDTO;
import com.project.khob.domain.dto.CartItemsDTO;
import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.User;
import com.project.khob.mappers.impl.CartItemsMapper;
import com.project.khob.repositories.CartRepository;
import com.project.khob.services.CartItemService;
import com.project.khob.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartItemService cartItemService;
    private final CartRepository cartRepository;
    private final CartItemsMapper cartItemsMapper;
    @Override
    public CartDTO getActiveCartItemsForUser(User user) {
        Cart cart = getNewestCartForUser(user);
        return cartItemService.getActiveItemsForCart(cart);
    }

    @Override
    public void addCartItemsToCart(CartItemsDTO cartItemsDTO, User user) {
        Cart cart = getNewestCartForUser(user);
        List<CartItem> cartItems = cartItemsMapper.mapFrom(cartItemsDTO);
        cartItems.forEach(cartItem ->
            cartItem.setCart(cart)

        );
        cartItemService.saveAll(cartItems);

    }

    @Override
    public void removeCartItemsFromCart(CartItemRequestDTO cartItemRequestDTO , User user) {
        List<Long> cartItemIds = cartItemRequestDTO.getCartItemIds();
        Cart cart = getNewestCartForUser(user);
        cart.getCartItems().forEach(cartItem -> {
            if (cartItemIds.contains(cartItem.getId()) && cartItem.getDateRemoved() == null) cartItem.setDateRemoved(new Date(System.currentTimeMillis()));
        });
        cartRepository.save(cart);
    }

    @Override
    public void clearCart(CartItemsDTO cartItemsDTO) {


    }

    @Override
    public Cart getNewestCartForUser(User user) {
        Optional<Cart> latestCart = cartRepository.getNewestCartByUserId(user.getId());
        return latestCart.orElseGet(() -> cartRepository.save(
                Cart.builder().user(user).build()));
    }
}
