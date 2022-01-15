package com.microservice.product.model;

import com.microservice.product.entity.Category;
import com.microservice.product.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel {
    private Long id;
    private String productName;
    private String description;
    private String thumbnail;
    private Double price;
    private BaseStatus status;
    private Long categoryId;
    private Category category;
}
