package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Category;
import com.project.khob.domain.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SubCategoryDto {
    private Integer subCategoryId;
    private String subCategory;

}
