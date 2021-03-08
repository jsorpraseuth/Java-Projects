import java.util.*;

public class Application {

    public static void main(String[] args) {

        // declarations
        List<Product> products = new ArrayList<Product>();
        SortingUtilityProxy sorter = new SortingUtilityProxy();

        // declaration of products
        Product product1 = new Product(55612, "Bag", 59.99);
        Product product2 = new Product(23466, "Book", 159.98);
        Product product3 = new Product(12362, "Calculator", 19.99);
        Product product4 = new Product(55262, "Pencil", 0.99);
        Product product5 = new Product(18992, "Notebook", 5.99);

        // add products into List
        products.add(product1);
        products.add(product2);
        products.add(product3);
        products.add(product4);
        products.add(product5);

        // call quicksort method
        sorter.sort(products, 1);
        // call bubblesort method
        sorter.sort(products, 0);
    }

}