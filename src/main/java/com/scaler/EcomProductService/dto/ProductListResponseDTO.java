package com.scaler.EcomProductService.dto;
import lombok.Getter;
import lombok.Setter;

import java.util.*;

@Getter
@Setter
public class ProductListResponseDTO {

    List<ProductResponseDTO> products;

    public ProductListResponseDTO(){
        this.products=new ArrayList<>();
    }
}
