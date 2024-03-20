package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.exception.*;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Category;
import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class ProductServiceImplTest {
    @Mock// we need a mock object of the given attribute
    private ProductRepository productRepository;

    @InjectMocks // this is the class we want to test, and this is where we would inject the mock objects
    private ProductServiceImpl productService;

    @BeforeEach
    public void setup(){
        System.out.println("Hello world,from BeforeEach");
        MockitoAnnotations.openMocks(this);
        // creates auto closeable resources for each test method
    }

    @Test
    public void testfindProductByTitleSuccess() throws InvalidTitleException, ProductNotFoundException {
        //Arrange
        Price mockprice=new Price();
        mockprice.setAmount(2000);

        Category category=new Category();
        category.setCategoryName("mockCategory");
        String testTitle=null;
//                "testProductTitle";
        Product mockProduct = new Product();
        mockProduct.setUuid(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockprice);
        mockProduct.setCategory(category);

        when(productRepository.findByTitle(any())).thenReturn(mockProduct);
        //Act
        ProductResponseDTO actualResponse=productService.findProductByTitle(testTitle);
        //assert
        Assertions.assertEquals(actualResponse.getTitle(),testTitle);
        Assertions.assertEquals(actualResponse.getPrice(),mockprice.getAmount());
        Assertions.assertEquals(actualResponse.getDescription(),mockProduct.getDescription());
        Assertions.assertEquals(actualResponse.getId(),mockProduct.getUuid());


    }
    @Test
    public void testFindByProductByTitle_RepoRespondsWithNullObject() throws ProductNotFoundException {
        //Arrange
        String testTitle = "testProductTitle";
        when(productRepository.findByTitle(testTitle)).thenReturn(null);
        Assertions.assertThrows(ProductNotFoundException.class, () -> productService.findProductByTitle(testTitle));
    }

    @Test
    public void testFindProductByTitle_NullTitle() throws ProductNotFoundException {
        //Arrange
        Price mockPrice = new Price();
        mockPrice.setAmount(100);

        Category mockCategory = new Category();
        mockCategory.setCategoryName("mockCategory");

        String testTitle = null;
        Product mockProduct = new Product();
        mockProduct.setUuid(UUID.randomUUID());
        mockProduct.setTitle(testTitle);
        mockProduct.setDescription("testDescription");
        mockProduct.setPrice(mockPrice);
        mockProduct.setCategory(mockCategory);
        when(productRepository.findByTitle(testTitle)).thenReturn(mockProduct);
        //Assert and Act
        Assertions.assertThrows(InvalidTitleException.class, () -> productService.findProductByTitle(testTitle) );
    }

}
