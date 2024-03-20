package com.scaler.EcomProductService.demo;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.*;

@Getter
@Setter
@Entity
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String name;
    @OneToMany(mappedBy = "author",cascade = CascadeType.ALL)
    private List<Book> books;

    public Author(){

    }
    public Author(String name,List<Book> books){
        this.name=name;
        this.books=books;
    }
}
