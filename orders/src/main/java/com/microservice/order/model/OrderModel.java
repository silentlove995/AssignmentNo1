package com.microservice.order.model;

import com.microservice.order.entity.OrderDetail;
import com.microservice.order.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class OrderModel {
    private Long customerId;
    private String customerName;
    private String phoneNumber;
    private String address;
    private Double totalPrice;
    private BaseStatus status;
    private Set<OrderDetail> orderDetails;
}
