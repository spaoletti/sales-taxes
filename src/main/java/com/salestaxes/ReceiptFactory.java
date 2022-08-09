package com.salestaxes;

import java.util.Arrays;
import java.util.List;

public class ReceiptFactory {
    public Receipt create(List<Product> shoppingBasket) {
        ReceiptLine line = new ReceiptLine(shoppingBasket.get(0));
        return new Receipt(Arrays.asList(line));
    }
}
