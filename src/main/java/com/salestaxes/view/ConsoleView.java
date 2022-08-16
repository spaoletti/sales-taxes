package com.salestaxes.view;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptLine;

import java.text.DecimalFormat;

public class ConsoleView extends View {
    @Override
    public void render(Receipt receipt) {
        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : receipt.getLines()) {
            sb.append(renderLine(line));
            sb.append('\n');
        }
        sb.append("Sales Taxes: ").append(df.format(receipt.getSalesTaxes())).append("\n");
        sb.append("Total: ").append(df.format(receipt.getTotal()));
        System.out.println(sb);
    }

    private String renderLine(ReceiptLine line) {
        return line.getQty() + " " + line.getDescription() + ": " + df.format(line.getGrossPrice());
    }

}
