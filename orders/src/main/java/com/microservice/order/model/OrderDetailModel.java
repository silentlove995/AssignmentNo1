package com.microservice.order.model;

import com.microservice.order.entity.Order;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderDetailModel {
    private Long productId;
    private Integer quantity;
    private Double unitPrice;
    private Long orderId;
    private Order order;
}
