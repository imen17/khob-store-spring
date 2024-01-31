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
@Table(name = "Category")
public class Category {

    @Id
    @GeneratedValue
    private Integer id;
    private String category;

    @OneToMany(mappedBy = "subCategory")
    private SubCategory subCategory;
}
