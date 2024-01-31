package com.project.khob.domain.dto;

import com.project.khob.domain.entities.SubCategory;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryDto {
    private Integer id;
    private String category;

    private SubCategory subCategory;
}
