package com.salestaxes.taxes;

import com.salestaxes.Product;

public class DefaultTaxStrategy implements TaxStrategy {
    @Override
    public double calculate(Product product) {
        double standardTaxes = (product.isExempt()) ? 0 : 10;
        double importTaxes = (product.isImported() ? 5 : 0);
        return standardTaxes + importTaxes;
    }
}
