package com.project.khob.controllers;

import com.project.khob.domain.dto.CartDTO;
import com.project.khob.domain.dto.CartItemRequestDTO;
import com.project.khob.domain.dto.CartItemsDTO;
import com.project.khob.services.CartService;
import com.project.khob.domain.entities.User;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    @GetMapping
    public ResponseEntity<CartDTO> getCart(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(cartService.getActiveCartItemsForUser(user));
    }

    @PutMapping(path = "/add")
    public ResponseEntity<Void> addToCart(@AuthenticationPrincipal User user, @Valid @RequestBody CartItemsDTO cartItemsDTO) {
        cartService.addCartItemsToCart(cartItemsDTO, user);
        return ResponseEntity.ok().build();
    }

    @PatchMapping(path = "/remove")
    public ResponseEntity<Void> removeFromCart(@AuthenticationPrincipal User user, @Valid @RequestBody CartItemRequestDTO cartItemRequestDTO) {
        cartService.removeCartItemsFromCart(cartItemRequestDTO, user);
        return ResponseEntity.ok().build();
    }

    @PostMapping(path = "/clear")
    public ResponseEntity<Void> clearCart(@Valid @RequestBody CartItemsDTO cartItemsDTO) {
        return ResponseEntity.ok().build();
    }
}
