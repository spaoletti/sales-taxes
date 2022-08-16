package com.salestaxes.receipts;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class Receipt {

    private final List<ReceiptLine> lines;
    private final double salesTaxes;
    private final double total;
    private final DecimalFormat df;

    /* About this class: I wanted it to be strictly a data class with no behaviour, and this is why it
     * accepts the totals in the constructor. This class can't be instanced outside this package; in order
     * to get an instance you have to use the ReceiptFactory, and that's how we prevent inconsistencies. */
    Receipt(List<ReceiptLine> lines, double salesTaxes, double total) {
        this.lines = Collections.unmodifiableList(lines);
        this.salesTaxes = salesTaxes;
        this.total = total;
        // Just to prevent locale surprises...
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", otherSymbols);
    }

    public List<ReceiptLine> getLines() {
        return lines;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : lines) {
            sb.append(line.toString(df));
            sb.append('\n');
        }
        sb.append("Sales Taxes: ").append(df.format(salesTaxes)).append("\n");
        sb.append("Total: ").append(df.format(total));
        return sb.toString();
    }

}
