package com.project.khob.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Item")
public class CartItem {

    @Id
    @GeneratedValue
    private Long cartItemId;

    @PositiveOrZero
    private Integer quantity;

    @NonNull
    private Date dateAdded = new Date();
    private Date dateRemoved;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("productVariantId")
    @JoinColumn(name = "productVariantId", referencedColumnName = "productVariantId")
    private ProductVariant productVariant;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart;
}

