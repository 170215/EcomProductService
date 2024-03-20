package com.scaler.EcomProductService.mapper;

import com.scaler.EcomProductService.dto.*;
import com.scaler.EcomProductService.model.Product;

import java.util.List;

//mapper is used for conversion of classes
public class ProductMapper
{

    public static FakeStoreProductRequestDTO productRequestToFakeStoreProductRequest(ProductRequestDTO productRequestDTO){
        FakeStoreProductRequestDTO fakeStoreProductRequestDTO= new FakeStoreProductRequestDTO();
        fakeStoreProductRequestDTO.setCategory(productRequestDTO.getCategory());
        fakeStoreProductRequestDTO.setDescription(productRequestDTO.getDescription());
        fakeStoreProductRequestDTO.setImage(productRequestDTO.getImage());
        fakeStoreProductRequestDTO.setPrice(productRequestDTO.getPrice());
        fakeStoreProductRequestDTO.setTitle(productRequestDTO.getTitle());
        return fakeStoreProductRequestDTO;
    }

    public static ProductResponseDTO fakeStoreProductResponseToProductResponse(FakeStoreProductResponseDTO fakeStoreProductResponseDTO){
        ProductResponseDTO productResponseDTO= new ProductResponseDTO();
        productResponseDTO.setCategory(fakeStoreProductResponseDTO.getCategory());
        productResponseDTO.setDescription(fakeStoreProductResponseDTO.getDescription());
        productResponseDTO.setImage(fakeStoreProductResponseDTO.getImage());
        productResponseDTO.setPrice(fakeStoreProductResponseDTO.getPrice());
        productResponseDTO.setTitle(fakeStoreProductResponseDTO.getTitle());
        productResponseDTO.setId(fakeStoreProductResponseDTO.getId());
        return productResponseDTO;
    }
    public static ProductListResponseDTO productListToProductResponse(List<Product> productList){

        ProductListResponseDTO productListResponseDTO= new ProductListResponseDTO();
        for(Product i:productList){
           ProductResponseDTO productResponseDTO=new ProductResponseDTO();
           productResponseDTO.setId(i.getUuid());
           productResponseDTO.setImage(i.getImage());
           productResponseDTO.setTitle(i.getTitle());
           productResponseDTO.setPrice(i.getPrice().getAmount());
           productResponseDTO.setDescription(i.getDescription());
           productResponseDTO.setCategory(i.getCategory().getCategoryName());
           productListResponseDTO.getProducts().add(productResponseDTO);
        }

        return productListResponseDTO;
    }

    public static ProductResponseDTO productToProductResponse(Product i) {
        ProductResponseDTO productResponseDTO=new ProductResponseDTO();
        productResponseDTO.setId(i.getUuid());
        productResponseDTO.setImage(i.getImage());
        productResponseDTO.setTitle(i.getTitle());
        productResponseDTO.setPrice(i.getPrice().getAmount());
        productResponseDTO.setDescription(i.getDescription());
        productResponseDTO.setCategory(i.getCategory().getCategoryName());
        return productResponseDTO;
    }


}
