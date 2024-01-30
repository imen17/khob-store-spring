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
@Table(name = "ShopOrder")
public class Order {
    @Id
    @GeneratedValue
    private Long orderNumber;
    private Date dateCreated;
    private Date dateCompleted;
    private Date dateCancelled;

    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;



    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "cartId", referencedColumnName = "cartId")
    private Cart cart;
}
