package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

@Builder
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue
    private Long id;

    private String name;

    private String description;

    private Integer price;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    private ProductStatus status = ProductStatus.ACTIVE;

    @ManyToOne
    @JoinColumn(name = "sub_category_id", referencedColumnName = "id")
    private SubCategory subCategory;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    private List<Photo> photos;
}
