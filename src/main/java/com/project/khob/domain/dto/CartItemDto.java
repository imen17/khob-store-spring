package com.project.khob.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDto {
    private Integer quantity;
    private Date dateAdded;
    private Date dateRemoved;

    private ProductVariantDto productVariant;

    private CartDto cart;
}
