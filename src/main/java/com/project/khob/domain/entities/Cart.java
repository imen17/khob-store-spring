package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Cart")
public class Cart {
    @Id
    @GeneratedValue
    private Long cartId;
    private Date dateCreated;
    private Date dateClosed;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId", referencedColumnName = "userId")
    private User user;

    @OneToMany(mappedBy = "cart")
    private List<CartItem> cartItems;

    @OneToOne(mappedBy = "cart")
    private Order order;

}
