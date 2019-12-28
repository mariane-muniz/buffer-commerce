package com.buffer.commerce.parameter;

public class AddToCartParameter extends ProductParameter {
    private int reduced;
    private int quantity;

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getReduced() {
        return reduced;
    }

    public void setReduced(int reduced) {
        this.reduced = reduced;
    }
}