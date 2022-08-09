package com.salestaxes;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.List;
import java.util.Locale;

public class Receipt {

    private List<ReceiptLine> lines;
    private double salesTaxes;
    private final double total;
    DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
    private final DecimalFormat df;


    public Receipt(List<ReceiptLine> lines, double salesTaxes, double total) {
        this.lines = lines;
        this.salesTaxes = salesTaxes;
        this.total = total;
        otherSymbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", otherSymbols);
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : lines) {
            sb.append(line.toString(df));
            sb.append('\n');
        }
        sb.append("Sales Taxes: " + df.format(salesTaxes) + "\n");
        sb.append("Total: " + df.format(total));
        return sb.toString();
    }

}
