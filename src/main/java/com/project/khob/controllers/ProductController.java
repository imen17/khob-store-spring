package com.project.khob.controllers;

import com.project.khob.domain.dto.ProductDTO;
import com.project.khob.services.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @GetMapping(path = "/")
    public ResponseEntity<Page<ProductDTO>> getProducts(@PageableDefault(size = 50) Pageable pageable) {
        return ResponseEntity.ok(productService.findAll(pageable));
    }

    @GetMapping(path = "/{productId}")
    public ResponseEntity<ProductDTO> getProduct(@PathVariable("productId") Long productId) {
        return ResponseEntity.ok(productService.findOne(productId));
    }

    @DeleteMapping(path = "/{productId}")
    public ResponseEntity<Void> deleteProduct(@PathVariable("productId") Long productId) {
        productService.deleteOne(productId);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/")
    public ResponseEntity<Void> getProduct(@Valid @RequestBody ProductDTO productDTO) {
        productService.save(productDTO);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @PatchMapping(path = "/{productId}")
    public ResponseEntity<Void> updateProduct(@Valid @RequestBody ProductDTO productDTO, @PathVariable("productId") Long productId) {
        productService.update(productId, productDTO);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
