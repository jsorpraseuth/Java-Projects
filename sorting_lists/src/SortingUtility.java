import java.util.*;

public class SortingUtility implements SortingUtilityIF {
    
    // constructor
    public SortingUtility() {}

    // determines which sorting algorithm to use
    public List<Product> sort(List<Product> items, int sortingApproach) {
        // calls bubbleSort
        if(sortingApproach == 0) {
            bubbleSort(items);
        }
        // calls quickSort
        if(sortingApproach == 1) {
            quickSort(items, 0, items.size()-1);
        }
        return items;
    }

    // bubblesort
    private List<Product> bubbleSort(List<Product> items) {
        Product temp;
        boolean sorted = false;

        while(!sorted) {
            sorted = true;
            for(int i = 0; i < items.size() - 1; i++) {
                // compare left index to right, if greater then swap
                if(items.get(i).getID() > items.get(i+1).getID()) {
                    temp = items.get(i);
                    items.set(i, items.get(i+1));
                    items.set(i+1, temp);
                    sorted = false;
                }
            }
        }

        return items;
    }

    // quicksort
    private List<Product> quickSort(List<Product> items, int low, int high) {
        List<Product> sorted = new ArrayList<Product>(items);
        int i = low;
        int j = high;
        String pivot = items.get(low + (high - low) / 2).getName();

        while(i <= j) {
            while(items.get(i).getName().compareTo(pivot) < 0 ) {
                i++;
            }
            while(items.get(j).getName().compareTo(pivot) > 0 ) {
                j--;
            }
            if(i <= j) {
                sorted.set(i, items.get(j));
                sorted.set(j, items.get(i));
                i++;
                j--;
            }
        }

        if(low < j) {
            quickSort(sorted, low, j);
        }
        if(i < high) {
            quickSort(sorted, i, high);
        }

        return sorted;
    }

}

