package com.microservice.order.service;

import com.microservice.order.model.OrderDetailModel;
import com.microservice.order.model.ResponseData;

public interface OrderDetailService {
    ResponseData findById(Long id);
    ResponseData findAll();
    ResponseData save(OrderDetailModel orderDetailModel);
    ResponseData update(OrderDetailModel orderDetailModel, Long id);
    ResponseData delete(Long id);
}
