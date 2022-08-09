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

    @Test
    public void
    create_a_receipt_with_properly_formatted_lines() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("bottle of perfume", 51.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        String expectedOutput =
                "1 bottle of perfume: 56.64\n" +
                "1 music CD: 16.49\n";
        assertTrue(output.contains(expectedOutput));
    }

    @Test
    public void
    create_a_receipt_with_the_price_with_taxes_of_a_single_imported_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("59.24"));
    }

    @Test
    public void
    create_a_receipt_with_no_taxes_for_an_exempt_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 51.49, true)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("51.49"));
    }

    @Test
    public void
    create_a_receipt_with_only_import_taxes_for_an_imported_exempt_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true),
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("54.09"));
    }

    @Test
    public void
    create_a_receipt_with_the_total_sales_taxes_line() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true),
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("Sales Taxes: 10.35"));
    }

    @Test
    public void
    create_a_receipt_with_the_grand_total_line() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true),
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        assertTrue(output.contains("Total: 113.33"));
    }

}
