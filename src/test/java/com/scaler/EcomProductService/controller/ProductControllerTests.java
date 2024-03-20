package com.scaler.EcomProductService.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.scaler.EcomProductService.dto.ErrorResponseDTO;
import com.scaler.EcomProductService.exception.*;
import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest
public class ProductControllerTests {
    @Autowired//helps us to make mock api calls
    private MockMvc mockMvc;

    @MockBean(name="productservice")//depending upon
    private ProductService productService;
    @Test
    public void getAllProductsReturnsEmptyListWhenNoProductsAvailable() throws Exception {
        ProductListResponseDTO emptyProductListResponse=new ProductListResponseDTO();
        when(productService.getAllProducts()).thenReturn(emptyProductListResponse);

        mockMvc.perform(get("/products")).andExpect(status().is(200)).andExpect(content().string("{\"products\":[]}"));
    }

    @Test
    public void getAllProductsReturnProducts() throws Exception {
        ProductListResponseDTO productListResponseDTO= new ProductListResponseDTO();
        ProductResponseDTO product1= new ProductResponseDTO();
        product1.setId(UUID.fromString("104e43a5-42e2-4114-a6ca-123027393fa9"));
        product1.setTitle("Laptop");
        product1.setCategory("Electronics");
        product1.setPrice(1000);
        product1.setImage("someIamgeURL");

        ProductResponseDTO product2= new ProductResponseDTO();
        product2.setId(UUID.randomUUID());
        product2.setTitle("Laptop");
        product2.setCategory("Electronics");
        product2.setPrice(1000);
        product2.setImage("someIamgeURL");
        String requestJson1=convertToJson(product1);
        String requestJson2=convertToJson(product2);
        productListResponseDTO.setProducts(List.of(product1,product2));
        when(productService.getAllProducts()).thenReturn(productListResponseDTO);
        //System.out.println(String.format("{\"products\":[%s]}", requestJson));

        mockMvc.perform(get("/products")).andExpect(status().is(200)).andExpect(content().string(String.format("{\"products\":[%s,%s]}", requestJson1,requestJson2)));

    }

@Test
void createProductSuccess() throws Exception {
    //arrange
    ProductRequestDTO productRequestDTO = new ProductRequestDTO();
    productRequestDTO.setTitle("Laptop");
    productRequestDTO.setCategory("Electronics");
    productRequestDTO.setDescription("Best laptop");
    productRequestDTO.setPrice(1000);
    productRequestDTO.setImage("someImageURL");

    ProductResponseDTO productResponseDTO = new ProductResponseDTO();
    productResponseDTO.setId(UUID.fromString("feecadf2-e74c-4a06-9e32-2e6d757158b2"));
    productResponseDTO.setTitle("Laptop");
    productResponseDTO.setCategory("Electronics");
    productResponseDTO.setDescription("Best laptop");
    productResponseDTO.setPrice(1000);
    productResponseDTO.setImage("someImageURL");

    String requestJson = convertToJson(productRequestDTO);
    String responseJson = convertToJson(productResponseDTO);
    System.out.println(responseJson);
    when(productService.createProduct(eq(productRequestDTO))).thenReturn(productResponseDTO);

    mockMvc.perform(post("/products")
                    .contentType(MediaType.APPLICATION_JSON)
                    .content(requestJson))//here we are not passing the actual object passing equivalent object
            .andExpect(status().is(200))
            .andExpect(content().string(responseJson));
}

    @Test
    void deleteProductSuceess() throws Exception {
        when(productService.deleteProduct(5)).thenReturn(true);

        mockMvc.perform(delete("/products/5")).andExpect(status().is(200)).andExpect(content().string("true"));
    }
    @Test
    void deleteProductFailure() throws ProductNotFoundException {
        when(productService.deleteProduct(1)).thenThrow(new ProductNotFoundException("Product Not Found"));

        try {
            mockMvc.perform(delete("/products/1")).andExpect(status().is(404))
                    .andExpect(content().string("{\"message\":\"Product Not Found\",\"messageCode\":404}"));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Test
    void findProductByIdFailure() throws ProductNotFoundException {
        when(productService.getProductById(1)).thenThrow(new ProductNotFoundException("Product Not Found"));
//        doThrow(new ProductNotFoundException("Product Not Found")).when(productService).getProductById(1);
        try {
            mockMvc.perform(get("/products/1"))
                    .andExpect(status().is(404))
                    .andExpect(content().string("{\"message\":\"Product Not Found\",\"messageCode\":404}"));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    private String convertToJson(Object product1) throws JsonProcessingException {

        ObjectMapper mapper= new ObjectMapper();
        return mapper.writeValueAsString(product1);

    }

}
