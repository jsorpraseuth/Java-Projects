import java.util.*;

public class SortingUtilityProxy implements SortingUtilityIF {
    SortingUtility sortingObject;

    public SortingUtilityProxy() {
        sortingObject = new SortingUtility();
    }

    // Proxy modified sort function from SortingUtility
    // Provides log output to terminal
    public List<Product> sort(List<Product> items, int sortingApproach) {
        // calls bubbleSort
        if(sortingApproach == 0) {
            sortingObject.sort(items, 0);

            // Print formatted with ID first
            System.out.println("----------------------------------------");
            System.out.printf("%25s\n", "Bubble Sort");
            System.out.println("----------------------------------------");
            System.out.printf("%-10s%-15s%s\n", "ID", "Name", "Price");
            System.out.println("----------------------------------------");
            for(int i = 0; i < items.size(); i++) {
                System.out.printf("%-10d%-15s%.2f\n", items.get(i).getID(), items.get(i).getName(), items.get(i).getPrice());
            }
            System.out.println("\n");
        }
        // calls quickSort
        if(sortingApproach == 1) {
            sortingObject.sort(items, 1);

            // Print formatted with Name first
            System.out.println("----------------------------------------");
            System.out.printf("%25s\n", "Quick Sort");
            System.out.println("----------------------------------------");
            System.out.printf("%-15s%-10s%s\n", "Name", "ID", "Price");
            System.out.println("----------------------------------------");
            for (int i = 0; i < items.size(); i++) {
                System.out.printf("%-15s%-10d%.2f\n", items.get(i).getName(), items.get(i).getID(), items.get(i).getPrice());
            }
            System.out.println("\n");
        }

        return items;
    }

}

