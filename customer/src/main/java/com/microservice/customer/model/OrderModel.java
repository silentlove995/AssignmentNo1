package com.microservice.customer.model;

import com.microservice.customer.entity.Customer;
import com.microservice.customer.entity.OrderDetail;
import com.microservice.customer.enums.BaseStatus;
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
    private Customer customer;
    private Set<OrderDetail> orderDetails;
}
