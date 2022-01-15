package com.microservice.product.service;

import com.microservice.product.model.ProductModel;
import com.microservice.product.model.ResponseData;

public interface ProductService {
    ResponseData save(ProductModel productModel);
    ResponseData findById(Long id);
    ResponseData update(ProductModel productModel, Long id);
    ResponseData delete(Long id);
    ResponseData findAll();
}
