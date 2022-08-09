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

    @Test
    public void
    test_case_2() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported box of chocolates", 10.00, true),
                new Product("imported bottle of perfume", 47.50)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        String expectedOutput =
                        "1 imported box of chocolates: 10.50\n" +
                        "1 imported bottle of perfume: 54.65\n" +
                        "Sales Taxes: 7.65\n" +
                        "Total: 65.15";
        assertEquals(expectedOutput, output);
    }

}
