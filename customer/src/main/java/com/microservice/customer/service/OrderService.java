package com.microservice.customer.service;


import com.microservice.customer.model.OrderModel;
import com.microservice.customer.model.ResponseData;

public interface OrderService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(OrderModel orderModel);
    ResponseData update(OrderModel orderModel, Long id);
    ResponseData delete(Long id);
}