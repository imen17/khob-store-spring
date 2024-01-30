package com.project.khob.controllers;

import com.project.khob.domain.dto.CartDto;
import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.Product;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.CartService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CartController {
    private final CartService cartService;
    private final Mapper<Cart, CartDto> cartMapper;

    public CartController(CartService cartService, Mapper<Cart, CartDto> cartMapper) {
        this.cartService = cartService;
        this.cartMapper = cartMapper;
    }



    @PostMapping(path = "/auth/carts/new")
    public ResponseEntity<CartDto> createCart(@RequestBody CartDto cartDto){
        Cart cart=cartMapper.mapFrom(cartDto);
        Cart savedCart=cartService.createCart(cart);
        return new ResponseEntity<>(cartMapper.mapTo(savedCart), HttpStatus.CREATED);
    }

    //listAll
    @GetMapping(path = "admin/carts")
    public Page<CartDto> listCarts(Pageable pageable){
        Page<Cart> carts=cartService.findAll(pageable);
        return carts.map(cartMapper::mapTo);
    }

    //listOne
    @GetMapping ("/auth/cart/{id}")
    public ResponseEntity<CartDto> getCart(@PathVariable("id") Long id){
        Optional<Cart> foundCart=cartService.findOne(id);
        return foundCart.map(cart ->{
            CartDto cartDto = cartMapper.mapTo(cart);
            return new ResponseEntity<>(cartDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PatchMapping ("/auth/cart/{id}")
    public ResponseEntity<CartDto> partialUpdateCart(@PathVariable("id") Long id, @RequestBody CartDto cartDto) {
        if (!cartService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Cart cart=cartMapper.mapFrom(cartDto);
        Cart savedCart=cartService.partialUpdate(id, cart);
        return new ResponseEntity<>(cartMapper.mapTo(savedCart),HttpStatus.OK);
    }


    @DeleteMapping("/auth/cart/{id}")
    public ResponseEntity<CartDto> deleteCart(@PathVariable("id") Long id){
        cartService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }



    }
