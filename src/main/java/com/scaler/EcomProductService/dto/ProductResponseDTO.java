package com.scaler.EcomProductService.dto;

import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Category;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

//outgoing data
@Getter
@Setter
public class ProductResponseDTO {
    private UUID id;
    private String title;
    private double price;
    private String category;
    private String description;
    private String image;
}
