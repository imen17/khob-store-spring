package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Cart;
import com.project.khob.domain.entities.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private Long phone;
    private UserRole role;
    private List<CartDto> carts;
    private List<TokenDto> tokens;
    private List<AddressDto> addresses;
}
