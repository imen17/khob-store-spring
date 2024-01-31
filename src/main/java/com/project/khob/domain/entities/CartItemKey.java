package com.project.khob.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;
import java.io.Serializable;

@Data
@Embeddable
public class CartItemKey implements Serializable{
    @Column(name = "productVariantId")
    private Long productVariantId;

    @Column(name = "cartId")
    private Long cartId;

}
