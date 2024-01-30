package com.project.khob.domain.dto;

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
        private Float total;
        private CartDto cart;

}
