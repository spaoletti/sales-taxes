package com.salestaxes;

import java.util.List;

public class Receipt {

    List<ReceiptLine> lines;

    public Receipt(List<ReceiptLine> lines) {
        this.lines = lines;
    }

    public String print() {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : lines) {
            sb.append(line);
        }
        return sb.toString();
    }

}
