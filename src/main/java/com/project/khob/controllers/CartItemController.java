package com.project.khob.controllers;

import com.project.khob.domain.dto.ApiErrorResponse;
import com.project.khob.domain.dto.CartItemDto;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.*;
import com.project.khob.services.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class CartItemController {
    private final CartItemService cartItemService;
    private final CartService cartService;
    private final ProductVariantService productVariantService;
    private final UserService userService;
    private final JwtService jwtService;
    private final Mapper<CartItem, CartItemDto> itemMapper;

    @PostMapping(path = "/cart/add/{product_variant_id}")
    public ResponseEntity<Object> addToCart(@RequestHeader("Authorization") String authHeader, @PathVariable("product_variant_id") Long product_variant_id){
        final String refreshToken = authHeader.substring(7);
        final String username = jwtService.extractUsername(refreshToken);
        if (username == null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var user = userService.findByEmail(username);
        if (user.isEmpty()) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
        var productVariant = productVariantService.findOne(product_variant_id);
        if (productVariant.isEmpty()) {
            return ResponseEntity.status(HttpStatus.GONE).body(ApiErrorResponse.builder().message("Product no longer exists").build());
        }
        if (productVariant.get().getStock() == 0) {
            return ResponseEntity.status(HttpStatus.GONE).body(ApiErrorResponse.builder().message("Product out of stock").build());
        }

        var cart = cartService.findByUser(user.get());
        var cartItem = CartItem.builder().dateAdded(new Date()).productVariant(productVariant.get()).cart(cart).build();
        cartItemService.create(cartItem);

        return ResponseEntity.status(HttpStatus.CREATED).body(itemMapper.mapTo(cartItem));
    }
}
