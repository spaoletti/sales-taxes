package com.salestaxes;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptFactory;
import com.salestaxes.taxes.DefaultTaxStrategy;
import com.salestaxes.view.ConsoleView;
import com.salestaxes.view.View;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import utils.MockStandardOutput;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleViewShould {

    View v;
    MockStandardOutput mockStdOut = new MockStandardOutput();

    @After
    public void afterEach() {
        mockStdOut.reset();
    }

    @Before
    public void beforeEach() {
        mockStdOut.capture();
        v = new ConsoleView();
    }

    private Receipt createReceipt(List<Product> shoppingBasket) {
        ReceiptFactory rf = new ReceiptFactory(
                new MonetaryCalculator(),
                new DefaultTaxStrategy()
        );
        return rf.create(shoppingBasket);
    }

    @Test
    public void
    display_a_receipt_with_properly_formatted_lines() {
        Receipt receipt = createReceipt(Arrays.asList(
                new Product("bottle of perfume", 51.49),
                new Product("music CD", 14.99)
        ));
        v.render(receipt);
        String expectedOutput =
                "1 bottle of perfume: 56.64\n" +
                "1 music CD: 16.49\n";
        assertTrue(mockStdOut.read().contains(expectedOutput));
    }

    @Test
    public void
    display_a_receipt_with_properly_formatted_prices() {
        Receipt receipt = createReceipt(Arrays.asList(
                new Product("book", 100)
        ));
        v.render(receipt);
        String expectedOutput =
                "1 book: 110.00\n" +
                "Sales Taxes: 10.00\n" +
                "Total: 110.00";
        assertTrue(mockStdOut.read().contains(expectedOutput));
    }

    @Test
    public void
    display_a_complete_receipt() {
        Receipt receipt = createReceipt(Arrays.asList(
                new Product("book", 12.49, true),
                new Product("music CD", 14.99),
                new Product("chocolate bar", 0.85, true)
        ));
        v.render(receipt);
        String expectedOutput =
                "1 book: 12.49\n" +
                "1 music CD: 16.49\n" +
                "1 chocolate bar: 0.85\n" +
                "Sales Taxes: 1.50\n" +
                "Total: 29.83";
        assertTrue(mockStdOut.read().contains(expectedOutput));
    }

}
