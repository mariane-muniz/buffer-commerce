package com.buffer.commerce.config;

import com.buffer.commerce.model.Event;
import com.buffer.commerce.mongo.Product;
import com.buffer.commerce.mongo.repository.ProductRepository;
import com.buffer.commerce.repository.EventRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("teste")
public class TestController {

    @Resource
    private ProductRepository productRepository;

    @GetMapping
    @ResponseBody
    public String test() {

        Product p = new Product();
        p.setCode("123");
        p.setName("Tenis nike");
        p.setQuantity(3);
        p.setPrice("25.00");

        this.productRepository.save(p);

        return "end";
    }
}
