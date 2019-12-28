package com.buffer.commerce.action.product;

import com.buffer.commerce.config.Action;
import com.buffer.commerce.config.DTO;
import com.buffer.commerce.config.Parameter;
import com.buffer.commerce.config.Status;
import com.buffer.commerce.mongo.Product;
import com.buffer.commerce.mongo.service.ProductService;
import com.buffer.commerce.parameter.ProductParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class ValidateProductAction implements Action {

    final static String MESSAGE = "Product with code [code] not exist.";

    @Autowired
    private ProductService productService;

    @Override
    public DTO validateParameter(Parameter parameter) {
        if (parameter instanceof ProductParameter) {
            final ProductParameter productParameter = (ProductParameter) parameter;
            if (StringUtils.isEmpty(productParameter.getCode()))
                return new DTO().set("Product code is not present.", Status.FAIL);
            return new DTO().setStatus(Status.SUCCESS);
        }
        return new DTO()
                .set("Invalid parameter, type: "
                        + parameter.getClass().getName(), Status.FAIL);
    }

    @Override
    public DTO execute(Parameter parameter) {
        final ProductParameter productParameter = (ProductParameter) parameter;
        final String productCode = productParameter.getCode();
        final Product product = this.productService.getProduct(productCode);
        if (Objects.nonNull(product))
            return new DTO().setStatus(Status.SUCCESS);
        System.out.println(MESSAGE.replace("[code]", productCode));
        return new DTO().set(MESSAGE.replace("[code]", productCode), Status.FAIL);
    }
}