package com.salestaxes;

public class Product {

    private final String description;
    private final double netPrice;
    private boolean exempt = false;

    public Product(String description, double netPrice) {
        this.description = description;
        this.netPrice = netPrice;
    }

    public Product(String description, double netPrice, boolean exempt) {
        this(description, netPrice);
        this.exempt = exempt;
    }

    public String getDescription() {
        return description;
    }

    public double getNetPrice() {
        return netPrice;
    }

    public boolean isImported() {
        return description.contains("imported");
    }

    public boolean isExempt() {
        return exempt;
    }

}
