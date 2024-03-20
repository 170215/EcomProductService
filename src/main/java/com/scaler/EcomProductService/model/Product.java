package com.scaler.EcomProductService.model;

import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "PRODUCT")
public class Product extends BaseModel {


    private String title;

    //private double price;
    @ManyToOne
    private Category category;

    private String description;
    private String image;
    @OneToOne
    private Price price;


}
/*
Product- Category :M:1

 */