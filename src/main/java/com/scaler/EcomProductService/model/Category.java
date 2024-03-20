package com.scaler.EcomProductService.model;


import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity(name = "CATEGORY")
public class Category extends BaseModel {

    public String categoryName;

}
