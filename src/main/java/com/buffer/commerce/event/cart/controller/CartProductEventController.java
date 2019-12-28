package com.buffer.commerce.event.cart.controller;

import com.buffer.commerce.config.DTO;
import com.buffer.commerce.config.Status;
import com.buffer.commerce.event.cart.constant.CartEventConstants;
import com.buffer.commerce.event.cart.data.AddToCartData;
import com.buffer.commerce.facade.EventFacade;
import com.buffer.commerce.parameter.AddToCartParameter;
import com.buffer.commerce.service.FeatureFlagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class CartProductEventController {

    @Autowired
    private EventFacade eventFacade;
    @Autowired
    private FeatureFlagService featureFlagService;

    @GetMapping(
            value = CartEventConstants.CART_PATH_PRODUCT_ADD,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ResponseBody
    public ResponseEntity<DTO> addToCart(
            @RequestParam final String productCode,
            @RequestParam final int quantity) {

        if (this.featureFlagService.active("cart-add_to_cart")) {

            if (this.eventFacade.validateEvent("cart-add_to_cart")) {
                final AddToCartParameter parameter = new AddToCartParameter();
                parameter.setCode(productCode);
                parameter.setQuantity(quantity);
                final DTO result = this.eventFacade.executeEvent("cart-add_to_cart", parameter);

                if (Status.SUCCESS.equals(result.getStatus()))
                    return ResponseEntity.status(HttpStatus.ACCEPTED).body(result);
                else if (Status.PARTIAL.equals(result.getStatus()))
                    return ResponseEntity.status(HttpStatus.MULTI_STATUS).body(result);
                else
                    return ResponseEntity.status(HttpStatus.CONFLICT).body(result);
            }
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new AddToCartData().set("Inactive event", Status.INACTIVE));
        }
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new AddToCartData().set("Inactive feature", Status.INACTIVE));
    }
}