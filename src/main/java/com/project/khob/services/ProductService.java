package com.project.khob.services;

import com.project.khob.domain.entities.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface ProductService {

    Product save(Product product);

    Page<Product> findAll(Pageable pageable);

    Optional<Product> findOne(Long id);

    boolean isExists(Long id);

    Product partialUpdate(Long id, Product product);

    void delete(Long id);

}
