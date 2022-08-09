package com.salestaxes.receipts;

import com.salestaxes.MonetaryCalculator;
import com.salestaxes.Product;

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
        double salesTaxes = 0;
        double total = 0;
        for (ReceiptLine l : lines) {
            salesTaxes = mc.add(salesTaxes, l.getTaxes());
            total = mc.add(total, l.getGrossPrice());
        }
        return new Receipt(lines, salesTaxes, total);
    }

    private Function<Product, ReceiptLine> mapProductToReceiptLine = product -> {
        double standardTaxes = (product.isExempt()) ? 0 : 10;
        double importTaxes = (product.isImported() ? 5 : 0);
        double totalTaxes = mc.add(standardTaxes, importTaxes);
        double taxAmount = mc.percent(product.getNetPrice(), totalTaxes);
        double grossPrice = mc.add(product.getNetPrice(), taxAmount);
        return new ReceiptLine(product, grossPrice, taxAmount);
    };

}
