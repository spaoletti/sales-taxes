package com.salestaxes.view;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptLine;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public class ConsoleView implements View {
    @Override
    public void render(Receipt receipt) {

        final DecimalFormat df;

        // Just to prevent locale surprises...
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", otherSymbols);


        StringBuilder sb = new StringBuilder();
        for (ReceiptLine line : receipt.getLines()) {
            sb.append(line.toString(df));
            sb.append('\n');
        }
        sb.append("Sales Taxes: ").append(df.format(receipt.getSalesTaxes())).append("\n");
        sb.append("Total: ").append(df.format(receipt.getTotal()));
        System.out.println(sb);
    }

}
