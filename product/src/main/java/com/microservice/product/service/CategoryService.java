package com.microservice.product.service;

import com.microservice.product.model.CategoryModel;
import com.microservice.product.model.ResponseData;

public interface CategoryService {
    ResponseData save(CategoryModel categoryModel);
    ResponseData findById(Long id);
    ResponseData update(CategoryModel categoryModel, Long id);
    ResponseData delete(Long id);
    ResponseData findAll();
}
