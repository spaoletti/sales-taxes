package com.salestaxes;

public class ReceiptLine {

    private final double grossPrice;
    private final double taxes;
    private String description;

    public ReceiptLine(Product product, double grossPrice, double taxes) {
        this.description = product.getDescription();
        this.grossPrice = grossPrice;
        this.taxes = taxes;
    }

    public double getTaxes() {
        return taxes;
    }

    @Override
    public String toString() {
        return "1 " + description + ": " + grossPrice;
    }

}
