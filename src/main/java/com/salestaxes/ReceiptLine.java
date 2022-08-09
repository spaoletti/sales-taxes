package com.salestaxes;

public class ReceiptLine {

    private String description;

    public ReceiptLine(Product product) {
        this.description = product.getDescription();
    }

    public String getDescription() {
        return description;
    }

}