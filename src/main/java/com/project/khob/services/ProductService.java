package com.project.khob.services;

import com.project.khob.domain.dto.ProductDTO;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public interface ProductService {

    Page<ProductDTO> findAll(Pageable pageable);
   ProductDTO findOne(Long productId) throws EntityNotFoundException;
    void save(ProductDTO productDTO);
    void update(Long productId, ProductDTO productDTO) throws EntityNotFoundException;
    void deleteOne(Long productId) throws EntityNotFoundException;


}
