package com.salestaxes;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptFactory;
import com.salestaxes.taxes.DefaultTaxStrategy;
import com.salestaxes.view.ConsoleView;
import com.salestaxes.view.View;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class ConsoleViewShould {

    View v;

    private ByteArrayOutputStream mockStdOut = new ByteArrayOutputStream();
    private static final PrintStream originalStdOut = System.out;

    @AfterClass
    public static void afterAll() {
        System.setOut(originalStdOut);
    }

    @Before
    public void beforeEach() {
        resetStandardOutput();
        v = new ConsoleView();
    }

    private void resetStandardOutput() {
        mockStdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockStdOut));
    }

    @Test
    public void
    display_a_receipt_with_properly_formatted_lines() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("bottle of perfume", 51.49),
                new Product("music CD", 14.99)
        );
        ReceiptFactory rf = new ReceiptFactory(
                new MonetaryCalculator(),
                new DefaultTaxStrategy()
        );
        Receipt receipt = rf.create(shoppingBasket);
        v.render(receipt);
        String expectedOutput =
                "1 bottle of perfume: 56.64\n" +
                "1 music CD: 16.49\n";
        assertTrue(mockStdOut.toString().contains(expectedOutput));
    }

}
