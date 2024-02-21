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
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue
    private Long id;

    @Builder.Default
    private Date dateCreated = new Date();

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private OrderStatus status = OrderStatus.PROCESSING;

    private Date dateCancelled;

    private Date dateCompleted;

    @OneToOne
    private Cart cart;
}
