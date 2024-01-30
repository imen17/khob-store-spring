package com.project.khob.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AddressDto {

    private Long Id;
    private String addressLine;
    private String city;
    private String governorate;
    private UserDto user;
}
