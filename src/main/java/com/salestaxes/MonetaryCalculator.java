package com.salestaxes;

import java.math.BigDecimal;

public class MonetaryCalculator {

    public double percent(double amount, double percent) {
        return BigDecimal.valueOf(amount)
                .multiply(BigDecimal.valueOf(percent))
                .divide(BigDecimal.valueOf(100))
                .doubleValue();
    }

}
