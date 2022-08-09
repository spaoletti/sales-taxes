package com.salestaxes;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(JUnitParamsRunner.class)
public class MonetaryCalculatorShould {

    @Test
    @Parameters({
            "5005, 15, 750.75",
            "100, 10, 10",
            "0, 10, 0",
    })
    public void
    apply_percentage(
            double amount, double percent, double expected) {
        MonetaryCalculator mc = new MonetaryCalculator();
        double percentage = mc.percent(amount, percent);
        assertEquals(expected, percentage);
    }

}
