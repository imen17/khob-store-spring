package com.project.khob.controllers;

import com.project.khob.domain.dto.CartItemDto;
import com.project.khob.domain.entities.CartItem;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.CartItemService;
import com.project.khob.services.ProductService;
import com.project.khob.services.UserService;
import com.project.khob.services.impl.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/")
@RequiredArgsConstructor
public class CartItemController {
    private final ProductService productService;
    private final UserService userService;
    private final JwtService jwtService;
    private final CartItemService cartItemService;
    private final Mapper<CartItem, CartItemDto> itemMapper;

//    @PostMapping(path = "/add_to_cart/${product_id}")
//    public ResponseEntity<Object> addToCart(@RequestHeader("Authorization") String authHeader, @PathVariable("item_id") Long product_id){
//        final String refreshToken = authHeader.substring(7);
//        final String username = jwtService.extractUsername(refreshToken);
//        if (username == null) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//        var user = userService.findByEmail(username);
//        if (user.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
//        }
//
//        var product = productService.findOne(product_id);
//        if (product.isEmpty()) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ApiErrorResponse.builder().message("Product doesn't exist").build());
//        }
//        if (product.getStock())
//
//        var itemKey = new CartItemKey()
//
//        return new ResponseEntity<>(itemMapper.mapTo(savedItem), HttpStatus.CREATED);
//    }
}
