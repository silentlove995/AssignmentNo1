package com.microservice.product.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import com.microservice.product.entity.Category;
import com.microservice.product.entity.Product;
import com.microservice.product.enums.BaseStatus;
import com.microservice.product.model.ProductModel;
import com.microservice.product.model.ResponseData;
import com.microservice.product.model.dto.ProductDto;
import com.microservice.product.repository.ProductRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {ProductServiceImpl.class})
@ExtendWith(SpringExtension.class)
class ProductServiceImplTest {
    @MockBean
    private ProductRepository productRepository;

    @Autowired
    private ProductServiceImpl productServiceImpl;

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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setDeleted(true);
        product.setDescription("The characteristics of someone or something");
        product.setId(123L);
        product.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setPrice(10.0);
        product.setProductName("Product Name");
        product.setStatus(BaseStatus.ACTIVE);
        product.setThumbnail("Thumbnail");
        product.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findProductByProductName((String) any())).thenReturn(new ArrayList<>());

        ProductModel productModel = new ProductModel();
        productModel.setProductName("Product Name");
        ResponseData actualSaveResult = this.productServiceImpl.save(productModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add product successfully, data=ProductDto(id=123, productName=Product"
                + " Name, description=The characteristics of someone or something, thumbnail=Thumbnail, price=10.0,"
                + " status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type, deleted=true, status=ACTIVE,"
                + " products=[])))", actualSaveResult.toString());
        assertEquals("Add product successfully", actualSaveResult.getMessage());
        Object data = actualSaveResult.getData();
        assertEquals("ProductDto(id=123, productName=Product Name, description=The characteristics of someone or something,"
                + " thumbnail=Thumbnail, price=10.0, status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type,"
                + " deleted=true, status=ACTIVE, products=[]))", data.toString());
        assertEquals(123L, ((ProductDto) data).getId().longValue());
        assertEquals("Thumbnail", ((ProductDto) data).getThumbnail());
        assertEquals(10.0, ((ProductDto) data).getPrice());
        assertEquals("The characteristics of someone or something", ((ProductDto) data).getDescription());
        assertEquals("Product Name", ((ProductDto) data).getProductName());
        assertEquals(BaseStatus.ACTIVE, ((ProductDto) data).getStatus());
        verify(this.productRepository).findProductByProductName((String) any());
        verify(this.productRepository, atLeast(1)).save((Product) any());
    }

    @Test
    void testSave2() {
        Category category = new Category();
        category.setCreatedAt(LocalDateTime.of(0, 1, 1, 1, 1));
        category.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        category.setDeleted(true);
        category.setId(123L);
        category.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        category.setName("Name");
        category.setProducts(new HashSet<>());
        category.setStatus(BaseStatus.ACTIVE);
        category.setType("Type");
        category.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setDeleted(true);
        product.setDescription("The characteristics of someone or something");
        product.setId(123L);
        product.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setPrice(10.0);
        product.setProductName("Product Name");
        product.setStatus(BaseStatus.ACTIVE);
        product.setThumbnail("Thumbnail");
        product.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        when(this.productRepository.save((Product) any())).thenReturn(product);
        when(this.productRepository.findProductByProductName((String) any())).thenReturn(new ArrayList<>());

        ProductModel productModel = new ProductModel();
        productModel.setProductName("Product Name");
        ResponseData actualSaveResult = this.productServiceImpl.save(productModel);
        assertEquals(200, actualSaveResult.getCode());
        assertEquals("ResponseData(code=200, message=Add product successfully, data=ProductDto(id=123, productName=Product"
                + " Name, description=The characteristics of someone or something, thumbnail=Thumbnail, price=10.0,"
                + " status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type, deleted=true, status=ACTIVE,"
                + " products=[])))", actualSaveResult.toString());
        assertEquals("Add product successfully", actualSaveResult.getMessage());
        Object data = actualSaveResult.getData();
        assertEquals("ProductDto(id=123, productName=Product Name, description=The characteristics of someone or something,"
                + " thumbnail=Thumbnail, price=10.0, status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type,"
                + " deleted=true, status=ACTIVE, products=[]))", data.toString());
        assertEquals(123L, ((ProductDto) data).getId().longValue());
        assertEquals("Thumbnail", ((ProductDto) data).getThumbnail());
        assertEquals(10.0, ((ProductDto) data).getPrice());
        assertEquals("The characteristics of someone or something", ((ProductDto) data).getDescription());
        assertEquals("Product Name", ((ProductDto) data).getProductName());
        assertEquals(BaseStatus.ACTIVE, ((ProductDto) data).getStatus());
        verify(this.productRepository).findProductByProductName((String) any());
        verify(this.productRepository, atLeast(1)).save((Product) any());
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setDeleted(true);
        product.setDescription("The characteristics of someone or something");
        product.setId(123L);
        product.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setPrice(10.0);
        product.setProductName("Product Name");
        product.setStatus(BaseStatus.ACTIVE);
        product.setThumbnail("Thumbnail");
        product.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Product> ofResult = Optional.of(product);
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualFindByIdResult = this.productServiceImpl.findById(123L);
        assertEquals(200, actualFindByIdResult.getCode());
        assertEquals(
                "ResponseData(code=200, message=Success, data=ProductDto(id=123, productName=Product Name, description=The"
                        + " characteristics of someone or something, thumbnail=Thumbnail, price=10.0, status=ACTIVE, categoryId=null,"
                        + " category=Category(name=Name, type=Type, deleted=true, status=ACTIVE, products=[])))",
                actualFindByIdResult.toString());
        assertEquals("Success", actualFindByIdResult.getMessage());
        Object data = actualFindByIdResult.getData();
        assertEquals("ProductDto(id=123, productName=Product Name, description=The characteristics of someone or something,"
                + " thumbnail=Thumbnail, price=10.0, status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type,"
                + " deleted=true, status=ACTIVE, products=[]))", data.toString());
        assertEquals(123L, ((ProductDto) data).getId().longValue());
        assertEquals("Thumbnail", ((ProductDto) data).getThumbnail());
        assertEquals(10.0, ((ProductDto) data).getPrice());
        assertEquals("The characteristics of someone or something", ((ProductDto) data).getDescription());
        assertEquals("Product Name", ((ProductDto) data).getProductName());
        assertEquals(BaseStatus.ACTIVE, ((ProductDto) data).getStatus());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testFindById2() {
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualFindByIdResult = this.productServiceImpl.findById(123L);
        assertEquals(404, actualFindByIdResult.getCode());
        assertEquals("Product ID: 123 not found", actualFindByIdResult.getMessage());
        assertEquals("", actualFindByIdResult.getData());
        verify(this.productRepository).findById((Long) any());
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setDeleted(true);
        product.setDescription("The characteristics of someone or something");
        product.setId(123L);
        product.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setPrice(10.0);
        product.setProductName("Product Name");
        product.setStatus(BaseStatus.ACTIVE);
        product.setThumbnail("Thumbnail");
        product.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        Optional<Product> ofResult = Optional.of(product);
        doNothing().when(this.productRepository).deleteById((Long) any());
        when(this.productRepository.findById((Long) any())).thenReturn(ofResult);
        ResponseData actualDeleteResult = this.productServiceImpl.delete(123L);
        assertEquals(200, actualDeleteResult.getCode());
        assertEquals("Delete product successfully", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.productRepository).deleteById((Long) any());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testDelete2() {
        doNothing().when(this.productRepository).deleteById((Long) any());
        when(this.productRepository.findById((Long) any())).thenReturn(Optional.empty());
        ResponseData actualDeleteResult = this.productServiceImpl.delete(123L);
        assertEquals(404, actualDeleteResult.getCode());
        assertEquals("Product ID: 123 not found", actualDeleteResult.getMessage());
        assertEquals("", actualDeleteResult.getData());
        verify(this.productRepository).findById((Long) any());
    }

    @Test
    void testFindAll() {
        when(this.productRepository.findAll()).thenReturn(new ArrayList<>());
        ResponseData actualFindAllResult = this.productServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode());
        assertEquals("Successfully", actualFindAllResult.getMessage());
        assertTrue(((Collection<Object>) actualFindAllResult.getData()).isEmpty());
        verify(this.productRepository).findAll();
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

        Product product = new Product();
        product.setCategory(category);
        product.setCreatedAt(LocalDateTime.of(1, 1, 1, 1, 1));
        product.setCreatedBy("Jan 1, 2020 8:00am GMT+0100");
        product.setDeleted(true);
        product.setDescription("The characteristics of someone or something");
        product.setId(123L);
        product.setModifiedBy("Jan 1, 2020 9:00am GMT+0100");
        product.setPrice(10.0);
        product.setProductName("Product Name");
        product.setStatus(BaseStatus.ACTIVE);
        product.setThumbnail("Thumbnail");
        product.setModifiedAt(LocalDateTime.of(1, 1, 1, 1, 1));

        ArrayList<Product> productList = new ArrayList<>();
        productList.add(product);
        productList.addAll(new ArrayList<>());
        when(this.productRepository.findAll()).thenReturn(productList);
        ResponseData actualFindAllResult = this.productServiceImpl.findAll();
        assertEquals(200, actualFindAllResult.getCode());
        assertEquals("ResponseData(code=200, message=Successfully, data=[ProductDto(id=123, productName=Product Name,"
                + " description=The characteristics of someone or something, thumbnail=Thumbnail, price=10.0,"
                + " status=ACTIVE, categoryId=null, category=Category(name=Name, type=Type, deleted=true, status=ACTIVE,"
                + " products=[]))])", actualFindAllResult.toString());
        assertEquals("Successfully", actualFindAllResult.getMessage());
        assertEquals(1, ((Collection<ProductDto>) actualFindAllResult.getData()).size());
        verify(this.productRepository).findAll();
    }
}

