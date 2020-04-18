package Lab5;

import java.io.File;
import java.util.PriorityQueue;

/**
 * класс, осуществляющий проверкй условий
 */

public class Check {

    public static void check(PriorityQueue<Product> pq) {
        int h = 0;
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getId() == null) {

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
            System.out.println("Ваш элемент не добавлен");
            System.out.println("Скорее всего вы ввели некорректные значения");
        } else {
            System.out.println("Ваш элемент успешно добавлен");
        }
    }

    public static void checkFiles(File file) {
        int size = 1000;
        Files[] fr = new Files[size];

            int i = 0;
            fr[i]=new
            Files(file);

        }
        }
        class Files {
            File name;

            public Files(File name) {
                this.name = name;
            }
        }



