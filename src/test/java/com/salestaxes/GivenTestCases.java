package com.salestaxes;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptFactory;
import com.salestaxes.taxes.DefaultTaxStrategy;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GivenTestCases {

    ReceiptFactory rf;

    @Before
    public void beforeEach() {
        rf = new ReceiptFactory(
                new MonetaryCalculator(),
                new DefaultTaxStrategy()
        );
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

    @Test
    public void
    test_case_3() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported bottle of perfume", 27.99),
                new Product("bottle of perfume", 18.99),
                new Product("packet of headache pills", 9.75, true),
                new Product("box of imported chocolates", 11.25, true)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        String expectedOutput =
                "1 imported bottle of perfume: 32.19\n" +
                "1 bottle of perfume: 20.89\n" +
                "1 packet of headache pills: 9.75\n" +
                "1 box of imported chocolates: 11.85\n" +
                "Sales Taxes: 6.70\n" +
                "Total: 74.68";
        assertEquals(expectedOutput, output);
    }

}
