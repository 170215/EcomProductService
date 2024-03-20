package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.exception.*;
import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.exception.InvalidTitleException;
import com.scaler.EcomProductService.exception.ProductNotFoundException;
import com.scaler.EcomProductService.mapper.ProductMapper;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("productservice")
public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public ProductListResponseDTO getAllProducts() {

        List<Product> productList = productRepository.findAll();
        ProductListResponseDTO productListResponseDTO = ProductMapper.productListToProductResponse(productList);
        return productListResponseDTO;
    }

    @Override
    public ProductResponseDTO getProductById(int id) {
        return null;
    }

    @Override
    public ProductResponseDTO createProduct(ProductRequestDTO productRequestDTO) {
        return null;
    }
    @Override
    public boolean deleteProduct(int id) {
        return true;
    }

    @Override
    public Product updateProduct(int id, Product updateproduct) {
        return null;
    }

    @Override
    public ProductResponseDTO findProductByTitle(String title) throws InvalidTitleException, ProductNotFoundException {
        //findAll() -> list of all products
        //findById()-> get the products by id
        if(title==null ||  title.isEmpty()){
           throw new InvalidTitleException("title is not valid");
        }
        Product product =productRepository.findByTitle(title);
        if(product==null){
            throw new ProductNotFoundException();
        }
        ProductResponseDTO productResponseDTO=ProductMapper.productToProductResponse(product);
        return productResponseDTO;
    }


}
