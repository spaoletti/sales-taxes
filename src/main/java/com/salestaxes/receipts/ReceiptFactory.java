package com.salestaxes.receipts;

import com.salestaxes.MonetaryCalculator;
import com.salestaxes.Product;
import com.salestaxes.taxes.TaxStrategy;

import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class ReceiptFactory {

    private MonetaryCalculator mc;
    private TaxStrategy ts;

    public ReceiptFactory(MonetaryCalculator mc, TaxStrategy ts) {
        this.mc = mc;
        this.ts = ts;
    }

    public Receipt create(List<Product> shoppingBasket) {
        List<ReceiptLine> lines = shoppingBasket.stream()
                .map(mapProductToReceiptLine)
                .collect(Collectors.toList());
        double salesTaxes = 0;
        double total = 0;
        for (ReceiptLine l : lines) {
            salesTaxes = mc.add(salesTaxes, l.getTaxes());
            total = mc.add(total, l.getGrossPrice());
        }
        return new Receipt(lines, salesTaxes, total);
    }

    private final Function<Product, ReceiptLine> mapProductToReceiptLine = product -> {
        double taxes = ts.calculate(product);
        double taxAmount = mc.percent(product.getNetPrice(), taxes);
        double grossPrice = mc.add(product.getNetPrice(), taxAmount);
        return new ReceiptLine(product, grossPrice, taxAmount);
    };

}
