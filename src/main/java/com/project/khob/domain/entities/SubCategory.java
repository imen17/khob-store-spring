package com.project.khob.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "SubCategory")
public class SubCategory {

    @Id
    @GeneratedValue
    private Integer subCategoryId;

    @NotBlank
    private String subCategory;

    @OneToMany(mappedBy = "subCategory")
    private Collection<Product> products;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "categoryId", referencedColumnName = "categoryId")
    private Category category;
}
