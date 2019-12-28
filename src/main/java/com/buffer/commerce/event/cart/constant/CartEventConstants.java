package com.buffer.commerce.event.cart.constant;

public interface CartEventConstants {

    /**
     * Main Path for the cart events
     */
    String CART_PATH = "/cart";

    /**
     * Path for product events on the cart
     */
    String CART_PATH_PRODUCT = CART_PATH + "/product";
    String CART_PATH_PRODUCT_ADD = "/cart/product/add";
}
