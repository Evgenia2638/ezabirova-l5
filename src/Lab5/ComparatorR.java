package Lab5;

import java.util.Comparator;

public class ComparatorR implements Comparator<Product> {

    @Override
    public int compare(Product c1, Product c2) {
        return (int) (c1.getId() - c2.getId());
    }

}