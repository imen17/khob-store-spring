package com.project.khob.services.impl;

import com.project.khob.domain.dto.ProductDTO;
import com.project.khob.domain.entities.Product;
import com.project.khob.mappers.impl.ProductMapper;
import com.project.khob.repositories.ProductRepository;
import com.project.khob.services.ProductService;
import com.project.khob.services.SubCategoryService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ProductMapper productMapper;
    private final SubCategoryService subCategoryService;

    public Page<ProductDTO> findAll(Pageable pageable) {
        return productRepository.findAll(pageable).map(productMapper::mapTo);
    }
    @Override
    public ProductDTO findOne(Long productId) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        return productMapper.mapTo(product.get());
    }

    @Override
    public void save(ProductDTO productDTO) {
        Product product = productMapper.mapFrom(productDTO);
        subCategoryService.findOne(product.getSubCategory().getId());
        productRepository.save(product);
    }

    @Override
    public void update(Long productId, ProductDTO productDTO) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        Product updatedProduct = productMapper.mapFrom(productDTO);
        updatedProduct.setId(productId);
        productRepository.save(updatedProduct);
    }

    @Override
    public void deleteOne(Long productId) throws EntityNotFoundException {
        Optional<Product> product = productRepository.findById(productId);
        if (product.isEmpty()) throw new EntityNotFoundException("Product doesn't exist");
        productRepository.deleteById(productId);
    }
}
