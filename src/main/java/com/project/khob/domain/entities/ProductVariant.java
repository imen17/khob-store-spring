package com.project.khob.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "ProductVariant")
public class ProductVariant {

    @Id
    @GeneratedValue
    private Long productVariantId;

    @NonNull
    private String size;

    @NonNull
    private String color;

    @PositiveOrZero
    private Integer stock = 0;

    @OneToOne(mappedBy = "productVariant")
    private CartItem cartItem;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

}
