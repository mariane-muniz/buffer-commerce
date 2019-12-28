package com.buffer.commerce.action.stock;

import com.buffer.commerce.config.Action;
import com.buffer.commerce.config.DTO;
import com.buffer.commerce.config.Parameter;
import com.buffer.commerce.config.Status;
import com.buffer.commerce.parameter.AddToCartParameter;
import com.buffer.commerce.mongo.Product;
import com.buffer.commerce.mongo.service.ProductService;
import com.buffer.commerce.parameter.ProductParameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class ValidateStockAction implements Action {

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
    public DTO execute(final Parameter parameter) {
        if (parameter instanceof AddToCartParameter) {
            final String productCode = ((AddToCartParameter) parameter).getCode();
            final Product product = this.productService.getProduct(productCode);

            if (Objects.nonNull(product)) {
                final int stockQuantity = product.getQuantity();

                if (stockQuantity < 1)
                    return new DTO().set("Product without stock.", Status.FAIL);
                else
                    return new DTO().set("Success", Status.SUCCESS);
            }
        }
        return new DTO().set("Invalid parameter for processing, receive X is necessary S.", Status.FAIL);
    }
}