package com.salestaxes;

import java.util.List;

public class Receipt {

    List<ReceiptLine> lines;

    public Receipt(List<ReceiptLine> lines) {
        this.lines = lines;
    }

    public void print() {
        for (ReceiptLine line : lines) {
            System.out.println(line);
        }
    }

}
