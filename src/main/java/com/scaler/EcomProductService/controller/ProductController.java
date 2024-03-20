package com.scaler.EcomProductService.controller;

import com.scaler.EcomProductService.exception.*;
import com.scaler.EcomProductService.dto.ProductListResponseDTO;
import com.scaler.EcomProductService.dto.ProductRequestDTO;
import com.scaler.EcomProductService.dto.ProductResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.scaler.EcomProductService.service.ProductService;

@RestController
public class ProductController {

  /*  @Autowired
    @Qualifier("productservice")
    private ProdcutService productService;
*/


     private final ProductService productService;
    @Autowired
    public ProductController(@Qualifier("productservice") ProductService prodcutService) {
        this.productService = prodcutService;
    }

    @GetMapping("/products")
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
        ProductListResponseDTO responseDTO=productService.getAllProducts();
        return ResponseEntity.ok(responseDTO);

    }
    @GetMapping("/products/{id}")
    public ResponseEntity getProductByid(@PathVariable("id") int id){

        ProductResponseDTO responseDTO=productService.getProductById(id);
        return ResponseEntity.ok(responseDTO);

    }
    @GetMapping("/products/title/{title}")

    public ResponseEntity getProductByTitle(@PathVariable("title") String title) throws ProductNotFoundException {


        ProductResponseDTO response = productService.findProductByTitle(title);
        return ResponseEntity.ok(response);


    }

    @PostMapping("/products")
    public ResponseEntity createProduct(@RequestBody ProductRequestDTO productRequestDTO){
        ProductResponseDTO responseDTO =productService.createProduct(productRequestDTO);
        return ResponseEntity.ok(responseDTO);
    }

    @DeleteMapping("/products/{id}")
    public ResponseEntity deleteProductById(@PathVariable("id") int id){

        boolean responseDTO =productService.deleteProduct(id);

        return ResponseEntity.ok(responseDTO);
    }






}
