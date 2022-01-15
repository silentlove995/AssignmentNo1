package com.microservice.product.service.impl;

import com.microservice.product.entity.Category;
import com.microservice.product.entity.Product;
import com.microservice.product.model.CategoryModel;
import com.microservice.product.model.ResponseData;
import com.microservice.product.model.dto.CategoryDto;
import com.microservice.product.model.dto.ProductDto;
import com.microservice.product.repository.CategoryRepository;
import com.microservice.product.service.CategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public ResponseData save(CategoryModel categoryModel) {
        List<Category> foundCategory = categoryRepository.findCategoryByName(categoryModel.getName().trim());
        if (foundCategory.size() > 0 ){
            return new ResponseData(403, "Category name already exits", "");
        }
        ModelMapper modelMapper = new ModelMapper();
        Category category = modelMapper.map(categoryModel, Category.class);
        category = categoryRepository.save(category);
        CategoryDto dto = CategoryDto.categoryDto(categoryRepository.save(category));
        return new ResponseData(200, "Add category successfully", dto);
    }

    @Override
    public ResponseData findById(Long id) {
        Optional<Category> category = categoryRepository.findById(id);
        if (!category.isPresent()){
            return new ResponseData(404, "Category ID: " + id + " not found","");
        }
        return new ResponseData(200, "Success", CategoryDto.categoryDto(category.get()));
    }

    @Override
    public ResponseData update(CategoryModel categoryModel, Long id) {
        if (!categoryRepository.findById(id).isPresent()){
            return new ResponseData(404, "Category ID: " + id + " not found", "");
        }
        Optional<Category> category = categoryRepository.findById(id)
                .map(item -> {
                    ModelMapper modelMapper = new ModelMapper();
                    Long categoryId = item.getId();
                    item = modelMapper.map(categoryModel, Category.class);
                    item.setId(categoryId);
                    return item;
                });
        category.ifPresent(value -> categoryRepository.save(value));
        return new ResponseData(200, "Update category successfully", category);
    }

    @Override
    public ResponseData delete(Long id) {
        if (!categoryRepository.findById(id).isPresent()){
            return new ResponseData(404, "Category ID: " + id +  " not found", "");
        }
        categoryRepository.deleteById(id);
        return new ResponseData(200, "Delete category successfully", "");
    }

    @Override
    public ResponseData findAll() {
        List<Category> categories = categoryRepository.findAll();
        List<CategoryDto> dtos = new ArrayList<>();
        for (Category item : categories) {
            dtos.add(CategoryDto.categoryDto(item));
        }
        return new ResponseData(200, "Successfully", dtos);
    }
}
