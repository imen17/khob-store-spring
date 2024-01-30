package com.project.khob.controllers;

import com.project.khob.domain.dto.ProductDto;
import com.project.khob.domain.entities.Product;
import com.project.khob.mappers.Mapper;
import com.project.khob.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ProductController {

    private ProductService productService;

    private Mapper<Product,ProductDto> productMapper;

    public ProductController(ProductService productService, Mapper<Product,ProductDto> productMapper){
        this.productService=productService;
        this.productMapper=productMapper;
    }

    //Create
    @PostMapping("/admin/products/new")
    public ResponseEntity<ProductDto> createProduct(@RequestBody ProductDto productDto){
        Product product=productMapper.mapFrom(productDto);
        Product savedProduct=productService.save(product);
        return new ResponseEntity<>(productMapper.mapTo(savedProduct), HttpStatus.CREATED);
    }


    //listAll
    @GetMapping(path = "/products")
    public Page<ProductDto> listProducts(Pageable pageable){
        Page<Product> products=productService.findAll(pageable);
        return products.map(productMapper::mapTo);
    }

    //listOne
    @GetMapping ("/products/{id}")
    public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
        Optional<Product> foundProduct=productService.findOne(id);
        return foundProduct.map(product ->{
            ProductDto productDto = productMapper.mapTo(product);
            return new ResponseEntity<>(productDto,HttpStatus.OK);
        }).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping ("/admin/products/{id}")
    public ResponseEntity<ProductDto> fullUpdateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto){
        if (!productService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        productDto.setId(id);
        Product product=productMapper.mapFrom(productDto);
        Product savedProduct=productService.save(product);
        return new ResponseEntity<>(productMapper.mapTo(savedProduct),HttpStatus.OK);
    }

    @PatchMapping ("/admin/products/{id}")
    public ResponseEntity<ProductDto> partialUpdateProduct(@PathVariable("id") Long id, @RequestBody ProductDto productDto) {
        if (!productService.isExists(id)){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        Product product=productMapper.mapFrom(productDto);
        Product savedProduct=productService.partialUpdate(id, product);
        return new ResponseEntity<>(productMapper.mapTo(savedProduct),HttpStatus.OK);
    }

    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable("id") Long id){
        productService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
