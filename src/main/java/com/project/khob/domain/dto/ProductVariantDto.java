package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Item;
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
    private Long id;
    private String Size;
    private String color;
    private Integer stock;
    private Item item;
    private Product product;


}
