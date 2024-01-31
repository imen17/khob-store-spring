package com.project.khob.domain.dto;

import com.project.khob.domain.entities.CartItem;
import com.project.khob.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductVariantDto {
    private Long productVariantId;
    private String size;
    private String color;
    private Integer stock;
}
