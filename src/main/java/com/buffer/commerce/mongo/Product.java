package com.buffer.commerce.mongo;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Getter
@Setter
@Document(collection = "products")
public class Product {

    @Id
    private String id;
    private String date;
    private String name;
    private String price;
    private List<String> images;
    private String code;
    private int quantity;

    public Product() {
    }

    public Product(final String name, final String price, final String code, final int quantity) {
        this.name = name;
        this.price = price;
        this.date = LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
        this.code = code;
        this.quantity = quantity;
    }
}