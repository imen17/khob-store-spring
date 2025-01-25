package com.project.khob.domain.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemDTO {

    @NotNull
    @PositiveOrZero
    private Long productVariantId;

    @NotBlank
    @NotNull
    private String name;

    @Positive
    private int price;

}
