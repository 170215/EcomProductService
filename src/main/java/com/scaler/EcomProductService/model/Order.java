package com.scaler.EcomProductService.model;

import java.util.*;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToMany;
import lombok.Data;


@Data
@Entity(name="Product_order")
public class Order extends BaseModel{
    @ManyToMany
    private List<Product> products;
}
