package com.salestaxes;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.Collections;

public class ReceiptShould {

    @Test
    public void
    print_properly_formatted_prices() {

        // TODO test that 1 product line is formatted properly

        Receipt r = new Receipt(Arrays.asList(), 100, 100);
        String output = r.print();
        String expectedOutput =
                "Sales Taxes: 100.00\n" +
                "Total: 100.00";
        Assertions.assertEquals(expectedOutput, output);
    }

}
