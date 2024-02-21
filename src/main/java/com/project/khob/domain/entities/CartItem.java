package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "cart_items")
public class CartItem {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private Date dateAdded = new Date();

    private Date dateRemoved;

    @ManyToOne
    @JoinColumn(name = "variant_id", referencedColumnName = "id")
    private Variant variant;

    @ManyToOne
    @JoinColumn(name = "cart_id", referencedColumnName = "id")
    private Cart cart;
}
