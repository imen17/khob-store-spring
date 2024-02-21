package com.project.khob.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDTO {

    @NotBlank
    private String name;

    private String description;

    @Positive
    @NotNull
    private Integer price;

    @Positive
    @NotNull
    private Long categoryId;

    private String categoryName;

    private List<String> photoUrls;
}
