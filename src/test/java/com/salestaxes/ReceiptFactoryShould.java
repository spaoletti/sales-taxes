package com.salestaxes;

import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptFactory;
import com.salestaxes.taxes.DefaultTaxStrategy;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class ReceiptFactoryShould {

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
    create_a_receipt_with_the_description_of_a_single_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals("book", receipt.getLines().get(0).getDescription());
    }

    @Test
    public void
    create_a_receipt_with_the_description_of_many_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals("book", receipt.getLines().get(0).getDescription());
        assertEquals("music CD", receipt.getLines().get(1).getDescription());
    }

    @Test
    public void
    create_a_receipt_with_the_description_and_single_quantity_of_many_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 12.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals("book", receipt.getLines().get(0).getDescription());
        assertEquals("1", receipt.getLines().get(0).getQty());
        assertEquals("music CD", receipt.getLines().get(1).getDescription());
        assertEquals("1", receipt.getLines().get(1).getQty());
    }

    @Test
    public void
    create_a_receipt_with_the_price_with_taxes_of_many_non_imported_non_exempt_products() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("bottle of perfume", 51.49),
                new Product("music CD", 14.99)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(56.64, receipt.getLines().get(0).getGrossPrice());
        assertEquals(16.49, receipt.getLines().get(1).getGrossPrice());
    }

    @Test
    public void
    create_a_receipt_with_the_price_with_taxes_of_a_single_imported_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(59.24, receipt.getLines().get(0).getGrossPrice());
    }

    @Test
    public void
    create_a_receipt_with_no_taxes_for_an_exempt_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 51.49, true)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(51.49, receipt.getLines().get(0).getGrossPrice());
    }

    @Test
    public void
    create_a_receipt_with_only_import_taxes_for_an_imported_exempt_product() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(54.09, receipt.getLines().get(0).getGrossPrice());
    }

    @Test
    public void
    create_a_receipt_with_the_total_sales_taxes_line() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true),
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(10.35, receipt.getSalesTaxes());
    }

    @Test
    public void
    create_a_receipt_with_the_grand_total_line() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("imported book", 51.49, true),
                new Product("imported bottle of perfume", 51.49)
        );
        Receipt receipt = rf.create(shoppingBasket);
        assertEquals(113.33, receipt.getTotal());
    }

    // TODO view
    @Test
    public void
    create_a_receipt_with_properly_formatted_prices() {
        List<Product> shoppingBasket = Arrays.asList(
                new Product("book", 100)
        );
        Receipt receipt = rf.create(shoppingBasket);
        String output = receipt.print();
        String expectedOutput =
                "1 book: 110.00\n" +
                "Sales Taxes: 10.00\n" +
                "Total: 110.00";
        Assertions.assertEquals(expectedOutput, output);
    }

    // TODO view
    @Test
    public void
    create_a_complete_receipt() {
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
