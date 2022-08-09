package com.salestaxes;

public class Product {

    private final String description;
    private final double netPrice;

    public Product(String description, double netPrice) {
        this.description = description;
        this.netPrice = netPrice;
    }

    public String getDescription() {
        return description;
    }

    public double getNetPrice() {
        return netPrice;
    }
}
