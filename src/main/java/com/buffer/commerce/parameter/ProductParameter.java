package com.buffer.commerce.parameter;

import com.buffer.commerce.config.Parameter;

public class ProductParameter extends Parameter {

    private String code;
    private StockParameter stock;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public StockParameter getStock() {
        return stock;
    }

    public void setStock(StockParameter stock) {
        this.stock = stock;
    }
}