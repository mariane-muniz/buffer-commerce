package com.buffer.commerce.repository;

import com.buffer.commerce.model.Cart;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "carts", path = "cart")
public interface CartRepository extends CrudRepository<Cart, Long> {
}
