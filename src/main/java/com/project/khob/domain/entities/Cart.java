package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private Date dateCreated = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @OneToMany
    private List<CartItem> cartItems;
}
