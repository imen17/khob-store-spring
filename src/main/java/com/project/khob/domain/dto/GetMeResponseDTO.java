package com.project.khob.domain.dto;

import com.project.khob.domain.entities.UserRole;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class GetMeResponseDTO {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    private String photoUrl;
    private Set<UserRole> roles;
}
