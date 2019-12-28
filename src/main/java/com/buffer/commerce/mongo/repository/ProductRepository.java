package com.buffer.commerce.mongo.repository;

import com.buffer.commerce.mongo.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepository extends MongoRepository<Product, String> {

    Product findProductByCode(final String code);
}