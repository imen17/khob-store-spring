package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Item")
public class CartItem {

    @EmbeddedId
    private CartItemKey itemId;
    private Integer quantity;
    private Date dateAdded;
    private Date dateRemoved;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId("productVariantId")
    @JoinColumn(name = "productVariantId", referencedColumnName = "productVariantId")
    private ProductVariant productVariant;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("cartId")
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart;
}

