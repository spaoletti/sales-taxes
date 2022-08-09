package com.salestaxes;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptFactory {

    private MonetaryCalculator mc;

    public ReceiptFactory(MonetaryCalculator mc) {
        this.mc = mc;
    }

    public Receipt create(List<Product> shoppingBasket) {
        List<ReceiptLine> lines = shoppingBasket.stream()
                .map(mapProductToReceiptLine)
                .collect(Collectors.toList());
        return new Receipt(lines);
    }

    private Function<Product, ReceiptLine> mapProductToReceiptLine = product -> {
        double percent = 10 + (product.isImported() ? 5 : 0);
        double taxes = mc.percent(product.getNetPrice(), percent);
        double grossPrice = mc.add(product.getNetPrice(), taxes);
        return new ReceiptLine(
                product,
                grossPrice
        );
    };

}
