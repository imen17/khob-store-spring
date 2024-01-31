package com.project.khob.repositories;

import com.project.khob.domain.entities.ProductVariant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductVariantRepository  extends JpaRepository<ProductVariant, Long>  {
}
