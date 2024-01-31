package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "SubCategory")
public class SubCategory {

    @Id
    @GeneratedValue
    private Integer id;
    private String subCategory;

    @OneToMany(mappedBy = "product")
    private Product product;

    @ManyToOne(cascade = CascadeType.ALL)
    @MapsId("CategoryId")
    @JoinColumn(name = "CategoryId", referencedColumnName = "CategoryId")
    private Category category;
}
