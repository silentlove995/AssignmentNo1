package com.microservice.order.service;

import com.microservice.order.model.OrderModel;
import com.microservice.order.model.ResponseData;

public interface OrderService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(OrderModel orderModel);
    ResponseData update(OrderModel orderModel, Long id);
    ResponseData delete(Long id);
}