package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.exception.*;
import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Product;

public interface ProductService {

    ProductListResponseDTO getAllProducts();
    ProductResponseDTO getProductById(int id);


    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO);
    boolean deleteProduct(int id);

    Product updateProduct(int id,Product updateproduct);

    ProductResponseDTO findProductByTitle(String title) throws InvalidTitleException, ProductNotFoundException;
}
