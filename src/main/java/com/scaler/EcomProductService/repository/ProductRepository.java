package com.scaler.EcomProductService.repository;

import com.scaler.EcomProductService.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.UUID;
@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
   // @Query("select p from PRODUCT p where p.title = ?1")
    Product findByTitle(String title);
//    @Query("select p from PRODUCT p where p.title = ?1 and p.description = ?2")
    Product findByTitleAndDescription(String title , String description);

    Product findByTitleOrDescription(String title , String description);
    Product findByPrice_AmountLessThanEqual(double amount);

    Product findByPrice_AmountGreaterThanEqual(double amount);

    //Product findByPriceBetweenStartPrice_AmountAndEndPrice_Amount(double startPrice,double endPrice);




}
