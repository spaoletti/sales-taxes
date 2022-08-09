package com.salestaxes;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ReceiptFactory {

    public Receipt create(List<Product> shoppingBasket) {
        List<ReceiptLine> lines = shoppingBasket.stream()
                .map(p -> new ReceiptLine(p))
                .collect(Collectors.toList());
        return new Receipt(lines);
    }

}
