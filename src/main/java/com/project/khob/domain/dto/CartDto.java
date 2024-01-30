package com.project.khob.domain.dto;

import com.project.khob.domain.entities.Item;
import com.project.khob.domain.entities.Order;
import com.project.khob.domain.entities.User;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
