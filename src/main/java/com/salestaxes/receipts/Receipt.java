package com.salestaxes.receipts;

import java.util.Collections;
import java.util.List;

public class Receipt {

    private final List<ReceiptLine> lines;
    private final double salesTaxes;
    private final double total;

    /* About this class: I wanted it to be strictly a data class with no behaviour, and this is why it
     * accepts the totals in the constructor. This class can't be instanced outside this package; in order
     * to get an instance you have to use the ReceiptFactory, and that's how we prevent inconsistencies. */
    Receipt(List<ReceiptLine> lines, double salesTaxes, double total) {
        this.lines = Collections.unmodifiableList(lines);
        this.salesTaxes = salesTaxes;
        this.total = total;
    }

    public List<ReceiptLine> getLines() {
        return lines;
    }

    public double getSalesTaxes() {
        return salesTaxes;
    }

    public double getTotal() {
        return total;
    }

}
