package com.buffer.commerce.action.cart;

import com.buffer.commerce.config.Action;
import com.buffer.commerce.config.DTO;
import com.buffer.commerce.config.Parameter;
import com.buffer.commerce.config.Status;
import com.buffer.commerce.parameter.AddToCartParameter;
import com.buffer.commerce.model.Cart;
import com.buffer.commerce.model.CartEntry;
import com.buffer.commerce.mongo.Product;
import com.buffer.commerce.mongo.service.ProductService;
import com.buffer.commerce.parameter.ProductParameter;
import com.buffer.commerce.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class ValidateCartProductQuantityAction implements Action {

    @Autowired
    private CartService cartService;
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
            final Cart cart = this.cartService.getCartByCode("123");
            final String requestedCode = ((AddToCartParameter) parameter).getCode();
//            final List<CartEntry> entries = cart.getEntries();
            final List<CartEntry> entries = new ArrayList<>();
            final Optional<CartEntry> entry = this.getEntry(entries, requestedCode);

            if (entry.isPresent()) {
                final int entryQty = entry.get().getQuantity();
                final int requestedQty = ((AddToCartParameter) parameter).getQuantity();
                final int stockQty = this.getProductQuantity(requestedCode);

                if((entryQty + requestedQty) > stockQty) {
                    final int toAdd = stockQty - (entryQty + requestedQty);
                    ((AddToCartParameter) parameter).setQuantity(toAdd);
                    return new DTO().set("Quantity reduced.", Status.PARTIAL);
                }
                return new DTO().setStatus(Status.SUCCESS);
            }
        }
        return new DTO().set("Invalid parameter for processing, receive X is necessary S.", Status.FAIL);
    }

    private int getProductQuantity(final String productCode) {
        final Product product = this.productService.getProduct(productCode);
        return product.getQuantity();
    }

    private Optional<CartEntry> getEntry(final List<CartEntry> entries, final String requestedCode) {
        if (!CollectionUtils.isEmpty(entries))
            return entries.parallelStream()
                    .filter(entry -> entry.getProductCode().equals(requestedCode)).findFirst();
        return Optional.empty();
    }
}
