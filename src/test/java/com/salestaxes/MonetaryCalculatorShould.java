package com.salestaxes;

import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.jupiter.api.Assertions.*;

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

    @Test
    @Parameters({
            "-5005, 15",
            "5005, -15"
    })
    public void
    throw_if_provided_negative_numbers(
            double amount, double percent) {
        MonetaryCalculator mc = new MonetaryCalculator();
        IllegalArgumentException e = assertThrows(
                IllegalArgumentException.class,
                () -> mc.percent(amount, percent)
        );
        assertEquals("Error: negative amount.", e.getMessage());
    }

    @Test
    @Parameters({
            "47.50, 15, 7.15",
    })
    public void
    round_up_to_the_nearest_five_cents(
            double amount, double percent, double expected) {
        MonetaryCalculator mc = new MonetaryCalculator();
        double percentage = mc.percent(amount, percent);
        assertEquals(expected, percentage);
    }

}
