package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Product;

import java.util.List;

public interface ProdcutService {

    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id);


    public Product createProduct(ProductRequestDTO productRequestDTO);
    Product deleteProduct(int id);

    Product updateProduct(int id,Product updateproduct);

}
