package com.salestaxes;

import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReceiptFactoryShould {

    ReceiptFactory rf;

    @Before
    public void beforeEach() {
        rf = new ReceiptFactory(new MonetaryCalculator());
    }

    @Test
    public void
    create_a_receipt_with_the_description_of_a_single_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("book"));
    }

    @Test
    public void
    create_a_receipt_with_the_description_of_many_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("book"));
        assertTrue(output.contains("music CD"));
    }

    @Test
    public void
    create_a_receipt_with_the_description_and_single_quantity_of_many_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("1 book"));
        assertTrue(output.contains("1 music CD"));
    }

    @Test
    public void
    create_a_receipt_with_the_price_with_taxes_of_many_non_imported_non_exempt_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("bottle of perfume", 51.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("56.64"));
        assertTrue(output.contains("16.49"));
    }

}
