package com.scaler.EcomProductService.service;

import com.scaler.EcomProductService.demo.Author;
import com.scaler.EcomProductService.demo.AuthorRepository;
import com.scaler.EcomProductService.demo.Book;
import com.scaler.EcomProductService.model.Category;
import com.scaler.EcomProductService.model.Order;
import com.scaler.EcomProductService.model.Price;
import com.scaler.EcomProductService.model.Product;
import com.scaler.EcomProductService.repository.CategoryRepository;
import com.scaler.EcomProductService.repository.OrderRepository;
import com.scaler.EcomProductService.repository.PriceRepository;
import com.scaler.EcomProductService.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class InitServiceImpl implements InitService{

    private ProductRepository productRepository;
    private OrderRepository orderRepository;

    private PriceRepository priceRepository;
    private CategoryRepository categoryRepository;
    private AuthorRepository authorRepository;

    public InitServiceImpl(ProductRepository productRepository, OrderRepository orderRepository, PriceRepository priceRepository, CategoryRepository categoryRepository, AuthorRepository authorRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
        this.priceRepository = priceRepository;
        this.categoryRepository = categoryRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public void intialise() {
        Category electronics = new Category();
        electronics.setCategoryName("Electronics");
        electronics = categoryRepository.save(electronics);

        Price priceIphone=new Price();
        priceIphone.setCurrency("INR");
        priceIphone.setAmount(100000);
        priceIphone.setDiscount(0);

        Price priceMacBook=new Price();
        priceMacBook.setCurrency("INR");
        priceMacBook.setAmount(250000);
        priceMacBook.setDiscount(0);

        Price priceWatch=new Price();
        priceWatch.setCurrency("INR");
        priceWatch.setAmount(50000);
        priceWatch.setDiscount(0);

        priceIphone=priceRepository.save(priceIphone);
        priceMacBook=priceRepository.save(priceMacBook);
        priceWatch=priceRepository.save(priceWatch);

        Product iphone =new Product();
        iphone.setTitle("Iphone15Pro");
        iphone.setDescription("Best Iphone ever");
        iphone.setImage("http://someImageURL");
        iphone.setPrice(priceIphone);
        iphone.setCategory(electronics);
        iphone=productRepository.save(iphone);

        Product macbook = new Product();
        macbook.setTitle("Macbook Pro 16");
        macbook.setDescription("Best macbook ever");
        macbook.setImage("http://someImageURl");
        macbook.setPrice(priceMacBook);
        macbook.setCategory(electronics);
        macbook = productRepository.save(macbook);

        Product watch = new Product();
        watch.setTitle("Watch Series 10");
        watch.setDescription("Best watch ever");
        watch.setImage("http://someImageURl");
        watch.setPrice(priceWatch);
        watch.setCategory(electronics);
        watch = productRepository.save(watch);

        Order order= new Order();
        order.setProducts(List.of(iphone,watch,macbook));
        order=orderRepository.save(order);


        Author author = new Author("Ashok Kumar",null);
        Book book1= new Book("book1",author);
        Book book2= new Book("book2",author);
        Book book3= new Book("book3",author);
        author.setBooks(List.of(book1,book2,book3));
        author =authorRepository.save(author); //cascade all -> all dependent objects of author will aslo get saved
    }
}
