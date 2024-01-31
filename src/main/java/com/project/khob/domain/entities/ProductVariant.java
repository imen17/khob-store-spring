package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "ProductVariant")
public class ProductVariant {

    private Long id;
    private String Size;
    private String color;
    private Integer stock;

    @OneToOne(mappedBy = "productVariant")
    private Item item;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("productId")
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;
}
