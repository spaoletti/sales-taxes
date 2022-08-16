package com.salestaxes.view;

import com.salestaxes.receipts.Receipt;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

public abstract class View {

    protected final DecimalFormat df;

    public View() {
        // Just to prevent locale surprises...
        DecimalFormatSymbols otherSymbols = new DecimalFormatSymbols(Locale.getDefault());
        otherSymbols.setDecimalSeparator('.');
        df = new DecimalFormat("0.00", otherSymbols);
    }

    public abstract void render(Receipt receipt);

}
