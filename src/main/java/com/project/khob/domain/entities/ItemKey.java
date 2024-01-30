package com.project.khob.domain.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Data
@Embeddable
public class ItemKey implements Serializable {
    @Column(name = "productId")
    private Long productId;

    @Column(name = "cartId")
    private Long cartId;


}
