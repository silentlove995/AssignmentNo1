package com.microservice.customer.service;


import com.microservice.customer.model.OrderDetailModel;
import com.microservice.customer.model.ResponseData;

public interface OrderDetailService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(OrderDetailModel orderDetailModel);
    ResponseData update(OrderDetailModel orderDetailModel, Long id);
    ResponseData delete(Long id);
}
