package com.project.khob.domain.dto;

import com.project.khob.domain.entities.OrderStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderDto {
        private Long number;
        private Date dateCreated;
        private Date dateCompleted;
        private Date dateCancelled;
        private OrderStatus orderStatus;
        private CartDto cart;

}
