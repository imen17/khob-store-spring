package com.project.khob.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.*;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Product")
public class Product {
    @Id
    @GeneratedValue
    private Long productId;

    @NotBlank
    private String name;

    private String description;

    @PositiveOrZero
    private Integer price = 0;

    @Enumerated
    private ProductStatus status;

    @OneToMany(mappedBy = "product")
    private List<Photo> photos;

    @OneToMany(mappedBy = "product")
    private List<ProductVariant> productVariants;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "subCategoryId", referencedColumnName = "subCategoryId")
    private SubCategory subCategory;
}
