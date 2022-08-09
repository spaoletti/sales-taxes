package com.salestaxes;

import java.util.List;

public class Receipt {

    private List<ReceiptLine> lines;
    private double salesTaxes;
    private final double total;

    public Receipt(List<ReceiptLine> lines, double salesTaxes, double total) {
        this.lines = lines;
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : lines) {
            sb.append(line);
            sb.append('\n');
        }
        sb.append("Sales Taxes: " + salesTaxes + "\n");
        sb.append("Total: " + total);
        return sb.toString();
    }


}
