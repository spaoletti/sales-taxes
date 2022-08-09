package com.salestaxes;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReceiptFactoryShould {

    ReceiptFactory rf = new ReceiptFactory();

    private ByteArrayOutputStream mockStdOut = new ByteArrayOutputStream();
    private static final PrintStream originalStdOut = System.out;

    @AfterClass
    public static void afterAll() {
        System.setOut(originalStdOut);
    }

    @Before
    public void beforeEach() {
        resetStandardOutput();
        rf = new ReceiptFactory();
    }

    private void resetStandardOutput() {
        mockStdOut = new ByteArrayOutputStream();
        System.setOut(new PrintStream(mockStdOut));
    }

    @Test
    public void
    create_a_receipt_with_the_description_of_a_single_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        receipt.print();
        assertTrue(mockStdOut.toString().contains("book"));
    }

}