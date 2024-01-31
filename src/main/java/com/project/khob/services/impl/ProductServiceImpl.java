package com.project.khob.services.impl;

import com.project.khob.domain.entities.Product;
import com.project.khob.repositories.ProductRepository;
import com.project.khob.services.ProductService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    /*@Override
    public List<Product> findAll() {
        return StreamSupport
                .stream(productRepository.findAll()
                                .spliterator(),false)
                .collect(Collectors.toList());
    }*/

    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public Optional<Product> findOne(Long id) {
        return productRepository.findById(id);
    }

    @Override
    public boolean isExists(Long id) {
        return productRepository.existsById(id);
    }

    @Override
    public Product partialUpdate(Long id, Product product) {
        product.setProductId(id);

        return productRepository.findById(id).map(existingProduct -> {
            Optional.ofNullable(product.getDescription()).ifPresent(existingProduct::setDescription);
            Optional.ofNullable(product.getName()).ifPresent(existingProduct::setName);
            Optional.ofNullable(product.getPrice()).ifPresent(existingProduct::setPrice);
            return productRepository.save(existingProduct);
        }).orElseThrow(()->new RuntimeException("Product doesn't exist"));

    }

    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }

}
