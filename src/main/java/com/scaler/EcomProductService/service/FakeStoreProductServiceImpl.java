package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {
        String getAllProductURL="https://fakestoreapi.com/products";
        RestTemplate restTemplate= restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO[]> productResponseArray=
                restTemplate.getForEntity(getAllProductURL, ProductResponseDTO[].class);
        ProductListResponseDTO responseDTO=new ProductListResponseDTO();
        for(ProductResponseDTO productResponse: productResponseArray.getBody()){
            responseDTO.getProducts().add(productResponse);
        }

        return responseDTO;

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
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {



        String createProductURL="https://fakestoreapi.com/products/";
        RestTemplate restTemplate =restTemplateBuilder.build();
        ResponseEntity<ProductResponseDTO> prductResopnse =restTemplate.postForEntity(createProductURL,productRequestDTO,ProductResponseDTO.class);
        return prductResopnse.getBody();
    }

    @Override
    public boolean deleteProduct(int id) {

        String deleteURL="https://fakestoreapi.com/products/"+id;
        RestTemplate restTemplate =restTemplateBuilder.build();
        restTemplate.delete(deleteURL);

        return true;
    }

    @Override
    public Product updateProduct(int id, Product updateproduct) {
        return null;
    }
}
