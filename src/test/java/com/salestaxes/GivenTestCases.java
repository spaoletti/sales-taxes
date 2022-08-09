package com.salestaxes;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GivenTestCases {

    ReceiptFactory rf;

    @Before
    public void beforeEach() {
        rf = new ReceiptFactory(new MonetaryCalculator());
    }

    @Test
    public void
    test_case_1() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49, true),
                new Product("music CD", 14.99),
                new Product("chocolate bar", 0.85, true)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        String expectedOutput =
                "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";
        assertEquals(expectedOutput, output);
    }

}
