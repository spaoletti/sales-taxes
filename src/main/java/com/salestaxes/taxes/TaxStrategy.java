package com.salestaxes.taxes;

import com.salestaxes.Product;

public interface TaxStrategy {
    double calculate(Product product);
}
