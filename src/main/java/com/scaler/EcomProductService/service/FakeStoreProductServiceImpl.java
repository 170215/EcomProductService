package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.client.FakeStoreApiClient;
import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.mapper.ProductMapper;
import com.scaler.EcomProductService.model.Product;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
//for importing method directly
import static com.scaler.EcomProductService.mapper.ProductMapper.*;

@Service("fakestoreproductservice")
public class FakeStoreProductServiceImpl implements ProductService {
    private RestTemplateBuilder restTemplateBuilder;
    private FakeStoreApiClient fakeStoreApiClient;

    public FakeStoreProductServiceImpl(RestTemplateBuilder restTemplateBuilder, FakeStoreApiClient fakeStoreApiClient) {
        this.restTemplateBuilder = restTemplateBuilder;
        this.fakeStoreApiClient = fakeStoreApiClient;
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

        FakeStoreProductRequestDTO fakeStoreProductRequestDTO= productRequestToFakeStoreProductRequest(productRequestDTO);
        FakeStoreProductResponseDTO fakeStoreProductResponseDTO=fakeStoreApiClient.createProduct(fakeStoreProductRequestDTO);

        return fakeStoreProductResponseToProductResponse(fakeStoreProductResponseDTO);
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

    @Override
    public ProductResponseDTO findProductByTitle(String name) {
        return null;
    }
}
