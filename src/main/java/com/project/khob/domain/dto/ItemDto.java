package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.Product;
import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ItemDto {
    private Integer quantity;
    private Date dateAdded;
    private Date dateRemoved;

    private ProductDto product;

    private CartDto cart;
}
