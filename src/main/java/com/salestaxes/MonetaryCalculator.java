package com.salestaxes;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class MonetaryCalculator {

    BigDecimal tick = BigDecimal.valueOf(0.05);

    public double percent(double amount, double percent) {
        if (amount < 0 || percent < 0) {
            throw new IllegalArgumentException("Error: negative amount.");
        }
        return roundUp(
                BigDecimal.valueOf(amount)
                .multiply(BigDecimal.valueOf(percent))
                .divide(BigDecimal.valueOf(100))
        ).doubleValue();
    }

    private BigDecimal roundUp(BigDecimal price) {
        return price
                .divide(tick, 9, RoundingMode.HALF_EVEN)
                .setScale(0, RoundingMode.CEILING)
                .multiply(tick);
    }

    public double add(double a, double b) {
        return BigDecimal.valueOf(a).add(BigDecimal.valueOf(b)).doubleValue();
    }
}
