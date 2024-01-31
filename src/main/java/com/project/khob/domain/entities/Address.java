package com.project.khob.domain.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

@Entity
@Table(name = "Address")
public class Address {

    @Id
    @GeneratedValue
    private Long addressId;

    @NotBlank
    private String addressLine;

    @NotBlank
    private String city;

    @NotBlank
    private String governorate;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User user;
}
