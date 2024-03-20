package com.scaler.EcomProductService.client;

import com.scaler.EcomProductService.dto.FakeStoreProductRequestDTO;
import com.scaler.EcomProductService.dto.FakeStoreProductResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

//wrapper on fakestoreproductapi
@Component
public class FakeStoreApiClient {

    private RestTemplateBuilder restTemplateBuilder;

    public FakeStoreApiClient(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplateBuilder = restTemplateBuilder;
    }
    public FakeStoreProductResponseDTO createProduct(FakeStoreProductRequestDTO productRequestDTO) {
        String createProductURL="https://fakestoreapi.com/products/";
        RestTemplate restTemplate =restTemplateBuilder.build();
        ResponseEntity<FakeStoreProductResponseDTO> prductResopnse =restTemplate.postForEntity(createProductURL,productRequestDTO, FakeStoreProductResponseDTO.class);
        return prductResopnse.getBody();
    }

}

