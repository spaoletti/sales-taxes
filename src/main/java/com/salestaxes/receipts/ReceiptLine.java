package com.salestaxes.receipts;

import com.salestaxes.Product;

public class ReceiptLine {

    private final double grossPrice;
    private final double taxes;
    private final String description;

    ReceiptLine(Product product, double grossPrice, double taxes) {
        this.description = product.getDescription();
        this.grossPrice = grossPrice;
        this.taxes = taxes;
    }

    public double getGrossPrice() {
        return grossPrice;
    }

    public double getTaxes() {
        return taxes;
    }

    public String getDescription() {
        return description;
    }

    public String getQty() {
        return "1";
    }

}
