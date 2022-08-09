package com.salestaxes;

import java.util.List;

public class Receipt {

    private List<ReceiptLine> lines;
    private double salesTaxes;

    public Receipt(List<ReceiptLine> lines, double salesTaxes) {
        this.lines = lines;
        this.salesTaxes = salesTaxes;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : lines) {
            sb.append(line);
            sb.append('\n');
        }
        sb.append("Sales Taxes: " + salesTaxes);
        return sb.toString();
    }


}
