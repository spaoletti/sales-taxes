package com.salestaxes;

public class ReceiptLine {

    private final double grossPrice;
    private String description;

    public ReceiptLine(Product product, double grossPrice) {
        this.description = product.getDescription();
        this.grossPrice = grossPrice;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "1 " + description + ": " + grossPrice;
    }

}
