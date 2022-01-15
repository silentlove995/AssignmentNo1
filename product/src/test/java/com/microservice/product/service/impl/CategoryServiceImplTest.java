package com.microservice.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservice.product.entity.Category;
import com.microservice.product.enums.BaseStatus;
import com.microservice.product.model.CategoryModel;
import com.microservice.product.model.ResponseData;
import com.microservice.product.model.dto.CategoryDto;
import com.microservice.product.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {CategoryServiceImpl.class})
@ExtendWith(SpringExtension.class)
class CategoryServiceImplTest {
    @MockBean
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryServiceImpl categoryServiceImpl;

    @Test
    void testSave() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=123, name=Name,"
                + " status=ACTIVE, products=[]))", actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testSave2() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 0));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=123, name=Name,"
                + " status=ACTIVE, products=[]))", actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testSave3() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel(123L, "Add category successfully", BaseStatus.ACTIVE,
                new HashSet<>());
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=123, name=Name,"
                + " status=ACTIVE, products=[]))", actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testSave4() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=123, name=Name,"
                + " status=ACTIVE, products=[]))", actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testSave5() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(1L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals(
                "ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=1, name=Name, status=ACTIVE,"
                        + " products=[]))",
                actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(1L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testSave6() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findCategoryByName((String) any())).thenReturn(new ArrayList<>());

        CategoryModel categoryModel = new CategoryModel(123L, "Add category successfully", BaseStatus.ACTIVE,
                new HashSet<>());
        categoryModel.setName("Name");
        ResponseData actualSaveResult = this.categoryServiceImpl.save(categoryModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add category successfully, data=CategoryDto(id=123, name=Name,"
                + " status=ACTIVE, products=[]))", actualSaveResult.toString());
        assertEquals("Add category successfully", actualSaveResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualSaveResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualSaveResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualSaveResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualSaveResult.getData()).getName());
        verify(this.categoryRepository).findCategoryByName((String) any());
        verify(this.categoryRepository, atLeast(1)).save((Category) any());
    }

    @Test
    void testFindById() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Category> ofResult = Optional.of(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualFindByIdResult = this.categoryServiceImpl.findById(123L);
        assertEquals(200, actualFindByIdResult.getCode());
        assertEquals(
                "ResponseData(code=200, message=Success, data=CategoryDto(id=123, name=Name, status=ACTIVE," + " products=[]))",
                actualFindByIdResult.toString());
        assertEquals("Success", actualFindByIdResult.getMessage());
        assertEquals(123L, ((CategoryDto) actualFindByIdResult.getData()).getId().longValue());
        assertEquals(BaseStatus.ACTIVE, ((CategoryDto) actualFindByIdResult.getData()).getStatus());
        assertTrue(((CategoryDto) actualFindByIdResult.getData()).getProducts().isEmpty());
        assertEquals("Name", ((CategoryDto) actualFindByIdResult.getData()).getName());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testFindById2() {
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualFindByIdResult = this.categoryServiceImpl.findById(123L);
        assertEquals(404, actualFindByIdResult.getCode());
        assertEquals("Category ID: 123 not found", actualFindByIdResult.getMessage());
        assertEquals("", actualFindByIdResult.getData());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testUpdate() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualUpdateResult = this.categoryServiceImpl.update(new CategoryModel(), 123L);
        assertEquals(404, actualUpdateResult.getCode());
        assertEquals("Category ID: 123 not found", actualUpdateResult.getMessage());
        assertEquals("", actualUpdateResult.getData());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testUpdate2() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.categoryRepository.save((Category) any())).thenReturn(category);
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualUpdateResult = this.categoryServiceImpl.update(new CategoryModel(), 123L);
        assertEquals(404, actualUpdateResult.getCode());
        assertEquals("Category ID: 123 not found", actualUpdateResult.getMessage());
        assertEquals("", actualUpdateResult.getData());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testDelete() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Category> ofResult = Optional.of(category);
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualDeleteResult = this.categoryServiceImpl.delete(123L);
        assertEquals(200, actualDeleteResult.getCode());
        assertEquals("Delete category successfully", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.categoryRepository).deleteById((Long) any());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.categoryRepository).deleteById((Long) any());
        when(this.categoryRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualDeleteResult = this.categoryServiceImpl.delete(123L);
        assertEquals(404, actualDeleteResult.getCode());
        assertEquals("Category ID: 123 not found", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.categoryRepository).findById((Long) any());
    }

    @Test
    void testFindAll() {
        when(this.categoryRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseData actualFindAllResult = this.categoryServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode());
        assertEquals("Successfully", actualFindAllResult.getMessage());
        assertTrue(((Collection<Object>) actualFindAllResult.getData()).isEmpty());
        verify(this.categoryRepository).findAll();
    }

    @Test
    void testFindAll2() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        ArrayList<Category> categoryList = new ArrayList<>();
        categoryList.add(category);
        categoryList.addAll(new ArrayList<>());
        when(this.categoryRepository.findAll()).thenReturn(categoryList);
        ResponseData actualFindAllResult = this.categoryServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode());
        assertEquals("Successfully", actualFindAllResult.getMessage());
        Object data = actualFindAllResult.getData();
        assertEquals(1, ((Collection<CategoryDto>) data).size());
        CategoryDto getResult = ((List<CategoryDto>) data).get(0);
        assertEquals(123L, getResult.getId().longValue());
        assertEquals(BaseStatus.ACTIVE, getResult.getStatus());
        assertTrue(getResult.getProducts().isEmpty());
        assertEquals("Name", getResult.getName());
        verify(this.categoryRepository).findAll();
    }
}

