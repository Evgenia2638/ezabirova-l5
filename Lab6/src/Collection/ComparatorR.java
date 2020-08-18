package Collection;

import java.util.Comparator;

public class ComparatorR implements Comparator<Product> {
    /**
     * @param c1
     * @param c2
     * @return
     */
    @Override
    public int compare(Product c1, Product c2) {
        return (int) (c1.getId() - c2.getId());
    }

}
