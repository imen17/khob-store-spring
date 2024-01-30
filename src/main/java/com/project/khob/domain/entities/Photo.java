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
@Table(name = "Photo")
public class Photo {
    @Id
    @GeneratedValue
    private Long id;
    private String url;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "productId", referencedColumnName = "productId")
    private Product product;

}
