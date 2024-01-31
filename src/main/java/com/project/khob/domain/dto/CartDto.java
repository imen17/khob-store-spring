package com.project.khob.domain.dto;

import com.project.khob.domain.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder

public class CartDto {
    private Long id;
    private Date dateCreated;
    private User user;
    private List<ItemDto> items;
    private OrderDto order;
}
