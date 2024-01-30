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
@Table(name = "Address")
public class Address {

    @Id
    private Long Id;
    private String addressLine;
    private String city;
    private String governorate;

    @OneToOne (cascade = CascadeType.ALL)
    @MapsId ("userId")
    @JoinColumn(name = "userId",referencedColumnName = "userId")
    private User User;
}
