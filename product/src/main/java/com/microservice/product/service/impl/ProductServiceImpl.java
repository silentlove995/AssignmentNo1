package com.microservice.product.service.impl;

import com.microservice.product.entity.Product;
import com.microservice.product.model.ProductModel;
import com.microservice.product.model.ResponseData;
import com.microservice.product.model.dto.ProductDto;
import com.microservice.product.repository.CategoryRepository;
import com.microservice.product.repository.ProductRepository;
import com.microservice.product.service.ProductService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ResponseData save(ProductModel productModel) {
        List<Product> foundProduct = productRepository.findProductByProductName(productModel.getProductName().trim());
        if (foundProduct.size() > 0){
            return new ResponseData(403, "Product name already exits", "");
        }
        ModelMapper modelMapper = new ModelMapper();
        Product product = modelMapper.map(productModel, Product.class);
        product = productRepository.save(product);
        ProductDto dto = ProductDto.productDto(productRepository.save(product));
        return new ResponseData(200, "Add product successfully", dto);
    }

    @Override
    public ResponseData findById(Long id) {
        Optional<Product> product = productRepository.findById(id);
        if (!product.isPresent()){
            return new ResponseData(404, "Product ID: " + id + " not found", "");
        }
        return new ResponseData(200, "Success", ProductDto.productDto(product.get()));
    }

    @Override
    public ResponseData update(ProductModel productModel, Long id) {
        if (!productRepository.findById(id).isPresent()){
            return new ResponseData(404, "Product ID: " + id + " not found", "");
        }
        Optional<Product> product = productRepository.findById(id)
                .map(item -> {
                    ModelMapper modelMapper = new ModelMapper();
                    Long productId = item.getId();
                    item = modelMapper.map(productModel, Product.class);
                    item.setId(productId);
                    return item;
                });
        product.ifPresent(value -> productRepository.save(value));
        return new ResponseData(200, "Update product successfully", product);
    }

    @Override
    public ResponseData delete(Long id) {
        if (!productRepository.findById(id).isPresent()){
            return new ResponseData(404, "Product ID: " + id + " not found", "");
        }
        productRepository.deleteById(id);
        return new ResponseData(200, "Delete product successfully", "");
    }

    @Override
    public ResponseData findAll() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> dtos = new ArrayList<>();
        for (Product item : products) {
            dtos.add(ProductDto.productDto(item));
        }
        return new ResponseData(200, "Successfully", dtos);
    }
}
