package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Product;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class VariantDTO {

    @Positive
    private Long productId;

    @NotNull
    private String size;

    @NotNull
    private String color;

    @PositiveOrZero
    private Integer stock;

}
