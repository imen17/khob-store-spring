package com.project.khob.domain.dto;

import com.project.khob.domain.entities.TokenType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class TokenDto {

    public Integer id;
    public String token;
    public TokenType tokenType;
    public boolean revoked;
    public boolean expired;
    public UserDto user;
}
