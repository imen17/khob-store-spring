package com.project.khob.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ProductDto {
    private Long id;
    private String name;
    private String description;
    private Float price;
    private List<String> tags;
    private ItemDto item;
}
