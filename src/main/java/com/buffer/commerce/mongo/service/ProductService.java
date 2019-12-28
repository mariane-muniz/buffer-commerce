package com.buffer.commerce.mongo.service;

import com.buffer.commerce.mongo.Product;
import com.buffer.commerce.mongo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void save(final String name, final String price) {
        Product p = new Product(name, price, "123", 5);
        p.setImages(this.getImages());
        this.productRepository.save(p);
    }

    public List<Product> getAll() {
        return this.productRepository.findAll();
    }

    public Product getProduct(final String code) {
        return this.productRepository.findProductByCode(code);
    }

    private List<String> getImages() {
        List<String> list = new ArrayList<>();
        list.add("img/a.jpg");
        list.add("img/b.jpg");
        list.add("img/c.jpg");
        return list;
    }
}