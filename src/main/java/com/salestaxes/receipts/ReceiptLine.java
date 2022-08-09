package com.salestaxes.receipts;

import com.salestaxes.Product;

import java.text.DecimalFormat;

class ReceiptLine {

    private final double grossPrice;
    private final double taxes;
    private String description;

    ReceiptLine(Product product, double grossPrice, double taxes) {
        this.description = product.getDescription();
        this.grossPrice = grossPrice;
        this.taxes = taxes;
    }

    public double getTaxes() {
        return taxes;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public String toString(DecimalFormat df) {
        return "1 " + description + ": " + df.format(grossPrice);
    }

}
