package com.project.khob.domain.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Collection;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue
    private Integer categoryId;
    @NonNull
    private String category;
    @OneToMany(mappedBy = "subCategory")
    private Collection<SubCategory> subCategory;
}
