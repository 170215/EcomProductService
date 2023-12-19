package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("productservice")
public class ProductServiceImpl implements ProdcutService{


    private RestTemplateBuilder restTemplateBuilder;

    public ProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        String getAllProductURL="https://fakestoreapi.com/products";
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<ProductListResponseDTO> productResponse=
                restTemplate.getForEntity(getAllProductURL, ProductListResponseDTO.class);
        return productResponse.getBody();

    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        String getAllProductURL="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO> productResponse=
                restTemplate.getForEntity(getAllProductURL, ProductResponseDTO.class);
        return productResponse.getBody();

    }

    @Override
    public Product createProduct(Product product) {
        return null;
    }

    @Override
    public Product deleteProduct(int id) {
        return null;
    }

    @Override
    public Product updateProduct(int id, Product updateproduct) {
        return null;
    }
}
