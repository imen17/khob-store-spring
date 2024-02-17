package com.project.khob.domain.dto;

import com.project.khob.domain.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class GetMeResponseDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String photoUrl;
    private Set<UserRole> roles;
}
