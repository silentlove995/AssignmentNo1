package com.microservice.product.model.dto;

import com.microservice.product.entity.Category;
import com.microservice.product.entity.Product;
import com.microservice.product.enums.BaseStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private String description;
    private String thumbnail;
    private double price;
    private BaseStatus status;
    private Long categoryId;
    private Category category;

    public static ProductDto productDto(Product product){
        ProductDto tmp = new ProductDto();
        tmp.setId(product.getId());
        tmp.setProductName(product.getProductName());
        tmp.setDescription(product.getDescription());
        tmp.setThumbnail(product.getThumbnail());
        tmp.setPrice(product.getPrice());
        tmp.setStatus(product.getStatus());
        tmp.setCategory(product.getCategory());
        return tmp;
    }
}
