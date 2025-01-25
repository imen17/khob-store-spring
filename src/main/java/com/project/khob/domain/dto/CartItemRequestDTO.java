package com.project.khob.domain.dto;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collections;
import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CartItemRequestDTO {

    @NotNull
    @Builder.Default
    private List<Long> cartItemIds = Collections.emptyList();

}
