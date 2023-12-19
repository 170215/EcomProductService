package com.scaler.EcomProductService.controller;

import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import com.scaler.EcomProductService.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.scaler.EcomProductService.service.ProdcutService;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@RestController
public class ProductController {

    @Autowired
    @Qualifier("productservice")
    private ProdcutService productService;

    @GetMapping("/prodcut")
    public ResponseEntity getAllproducts(){
        /* ProductResponseDTO p1= new ProductResponseDTO();
        p1.setId(1);
        p1.setTitle("Iphone 15 pro");
        p1.setPrice(150000);
        p1.setImage("www.google.com/images/iphone");
        p1.setDescription("kafi mahanga phone");
        p1.setCategory("Electronics");
        ProductResponseDTO p2= new ProductResponseDTO();
        p2.setId(1);
        p2.setTitle("Macbook pro");
        p2.setPrice(250000);
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("kafi mahanga laptop");
        p2.setCategory("Electronics");
        List<ProductResponseDTO> products = Arrays.asList(p1,p2);
        return ResponseEntity.ok(products);
       */
        ProductResponseDTO responseDTO=productService.getProductById(1);
        return null;

    }
    @GetMapping("/prodcut/1")
    public ResponseEntity getProductByid(){
        /* ProductResponseDTO p1= new ProductResponseDTO();
        p1.setId(1);
        p1.setTitle("Iphone 15 pro");
        p1.setPrice(150000);
        p1.setImage("www.google.com/images/iphone");
        p1.setDescription("kafi mahanga phone");
        p1.setCategory("Electronics");
        ProductResponseDTO p2= new ProductResponseDTO();
        p2.setId(1);
        p2.setTitle("Macbook pro");
        p2.setPrice(250000);
        p2.setImage("www.google.com/images/macbook");
        p2.setDescription("kafi mahanga laptop");
        p2.setCategory("Electronics");
        List<ProductResponseDTO> products = Arrays.asList(p1,p2);
        return ResponseEntity.ok(products);
       */
        ProductResponseDTO responseDTO=productService.getProductById(1);
        return ResponseEntity.ok(responseDTO);

    }
}
