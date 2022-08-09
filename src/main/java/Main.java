import com.salestaxes.MonetaryCalculator;
import com.salestaxes.Product;
import com.salestaxes.receipts.Receipt;
import com.salestaxes.receipts.ReceiptFactory;
import com.salestaxes.taxes.DefaultTaxStrategy;

import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        ReceiptFactory rf = new ReceiptFactory(
                new MonetaryCalculator(),
                new DefaultTaxStrategy()
        );

        System.out.println("TEST CASE 1");
        System.out.println("-----------");
        List<Product> shoppingBasket1 = Arrays.asList(
                new Product("book", 12.49, true),
                new Product("music CD", 14.99),
                new Product("chocolate bar", 0.85, true)
        );
        Receipt receipt1 = rf.create(shoppingBasket1);
        System.out.println(receipt1.print());

        System.out.println("\nTEST CASE 2");
        System.out.println("-----------");
        List<Product> shoppingBasket2 = Arrays.asList(
                new Product("imported box of chocolates", 10.00, true),
                new Product("imported bottle of perfume", 47.50)
        );
        Receipt receipt2 = rf.create(shoppingBasket2);
        System.out.println(receipt2.print());

        System.out.println("\nTEST CASE 3");
        System.out.println("-----------");
        List<Product> shoppingBasket3 = Arrays.asList(
                new Product("imported bottle of perfume", 27.99),
                new Product("bottle of perfume", 18.99),
                new Product("packet of headache pills", 9.75, true),
                new Product("box of imported chocolates", 11.25, true)
        );
        Receipt receipt3 = rf.create(shoppingBasket3);
        System.out.println(receipt3.print());


    }
}