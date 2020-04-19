package Lab5;

import java.util.PriorityQueue;

public class CheckFile {

        public static void check(PriorityQueue<Product> pq) {
            int h = 0;
            Object[] products = pq.toArray();
            for (Object o : products) {
                Product product = (Product) o;
                if (product.getId() == null) {
                    h++;
                }
                if (product.getName() == null) {
                    h++;
                }
                if (product.getCoordinates() == null) {
                    h++;
                }
                if (product.getPrice() < 0) {
                    h++;
                }
                if (product.getUnitOfMeasure() == null) {
                    h++;
                }
                if (!(h == 0)) {
                    pq.remove(product);
                }
            }
            if (!(h == 0)) {
                System.out.println("Данные в файле находятся в некорректном формате, поэтому их больше нет");
                System.err.println("Я мухожук");
            } else {
                System.out.println("Элементы успешно дабавлены");
            }
        }
    }