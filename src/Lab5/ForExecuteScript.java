package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.PriorityQueue;

/**
 *Класс, содержащий часть команд для коррктного чтения информации из файла
 */


public class ForExecuteScript {

    public static void add(PriorityQueue<Product> pq, File file) throws IOException {
        FileReader fr = new FileReader(String.valueOf(file));
        Object[] products = pq.toArray();
        Product product = new Product();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        try {
            String name = reader.readLine();
            product.setName(name);
            person.setName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            coordinates.setX(Double.parseDouble(reader.readLine()));
        } catch (NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            coordinates.setY(Long.parseLong(reader.readLine()));
            product.setCoordinates(coordinates);
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setWeight((int) Double.parseDouble(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            product.setPrice(Float.parseFloat(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setEyeColor(Color.valueOf(reader.readLine()));
        } catch (NullPointerException | IllegalArgumentException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setHairColor(Color.valueOf(reader.readLine()));
        } catch (NullPointerException | IllegalArgumentException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setNationality(Country.valueOf(reader.readLine()));
            product.setOwner(person);
        } catch (NullPointerException | IllegalArgumentException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            product.setUnitOfMeasure(UnitOfMeasure.valueOf(reader.readLine()));
        } catch (NullPointerException | IllegalArgumentException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        product.setCreationDate(new Date());
        long[] id = new long[1];
        product.setId((long) id.hashCode());
        int h = 0;
        pq.add(product);
    }

    public static void add_if_max(PriorityQueue<Product> pq, File file) throws Exception {
        int h = 0;
        FileReader fr = new FileReader(String.valueOf(file));
        Object[] products = pq.toArray();
        BufferedReader reader = new BufferedReader(fr);
        String line = reader.readLine();
        float price2 = 0;
        // Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() > price2) {
                price2 = product.getPrice();
            }
        }
        String str2 = reader.readLine();
        float price1 = Float.parseFloat(str2);
        Product product = new Product();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        try {
            String name = reader.readLine();
            product.setName(name);
            person.setName(name);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            coordinates.setX(Double.parseDouble(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }

        try {
            coordinates.setY(Long.parseLong(reader.readLine()));
            product.setCoordinates(coordinates);
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setWeight((int) Double.parseDouble(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setEyeColor(Color.valueOf(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setHairColor(Color.valueOf(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            person.setNationality(Country.valueOf(reader.readLine()));
            product.setOwner(person);
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        try {
            product.setUnitOfMeasure(UnitOfMeasure.valueOf(reader.readLine()));
        } catch (NullPointerException | NumberFormatException ignored) {

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        product.setPrice(price1);
        product.setCreationDate(new Date());
        long[] id = new long[1];
        product.setId((long) Arrays.hashCode(id));
        if (price1 > price2) {
            pq.add(product);
            Check.check(pq);
        } else {
            System.out.println("Ваш элемент не добавлен");
            System.out.println("Значение вашего элемента не превышает значение наибольшего значения из этой коллекции");
        }

    }

    public static void count_less_than_price(PriorityQueue<Product> pq, File file) throws IOException {
        int h = 0;
        FileReader fr = new FileReader(String.valueOf(file));
        BufferedReader reader = new BufferedReader(fr);
        String str1 = reader.readLine();
        float price2 = Float.parseFloat(str1);
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() < price2) {
                System.out.println(o);
                h++;
            }
        }
        if (h == 0) {
            System.out.println("В коллекци нет таких элментов, чтобы значение price было меньше чем " + price2);
        }
    }

    public static void filter_greater_than_price(PriorityQueue<Product> pq, File file) throws IOException {
        int h = 0;
        FileReader fr = new FileReader(String.valueOf(file));
        BufferedReader reader = new BufferedReader(fr);
        String str1 = reader.readLine();
        float price2 = Float.parseFloat(str1);
        Object[] products = pq.toArray();
        for (int x = 0; x > products.length; x++) {
            Product product = (Product) products[x];
            if (product.getPrice() < price2) {
                System.out.println((products[x]));
                h++;
            }
        }
        if (h == 0) {
            System.out.println("В коллекци нет таких элментов, чтобы значение price было больше чем " + price2);
        }
    }

    public static void remove_greater(PriorityQueue<Product> pq, File file) throws IOException {
        int h = 0;
        FileReader fr = new FileReader(String.valueOf(file));
        BufferedReader reader = new BufferedReader(fr);
        String X1 = reader.readLine();
        String Y1 = reader.readLine();
        String str1 = reader.readLine();
        float price2 = Float.parseFloat(str1);
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() < price2) {
                pq.remove(product);
                h++;
            }
        }
        if (h < pq.size()) {
            System.out.println("Все элементы больше заданных величин");
        } else {
            System.out.println("Успешно удалено " + h + " элемента/ов");
        }
    }

    public static void execute_script_for_file(PriorityQueue<Product> pq, File file1) throws IOException {
        FileReader fr1 = new FileReader(file1);
        BufferedReader reader1 = new BufferedReader(fr1);
        CheckForLoops.add(String.valueOf(file1));
        try {
            FileReader fr = new FileReader(file1);
            Object[] products = pq.toArray();
            boolean exit = true;
            while (exit) {
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                switch (line.toLowerCase()) {
                    case ("add"):
                        ForExecuteScript.add(pq, file1);
                        Check.check(pq);
                        break;
                    case ("info"):
                        Comands.info(pq);
                        break;
                    case ("show"):
                        if (pq.size() > 0) {
                            Comands.show(pq);
                        } else {
                            System.out.println("Коллекция пуста");
                        }
                        break;
                    case ("remove_first"):
                        Comands.remove_first(pq);
                        System.out.println("Объект удален");
                        break;
                    case ("help"):
                        System.out.println("Информация о доступных командах:" +
                                "\ninfo: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                                "\nadd : добавить новый элемент в коллекцию" +
                                "\nshow: вывести в стандартный поток вывода все элементы коллекции в строковом представлении" +
                                "\nclear: удалить из коллекции все элементы" +
                                "\nremove_first: удалить первый элемент из коллекции" +
                                "\nremove_by_id: удалить значение элемента по его id" +
                                "\nupdate_id:  обновить значение элемента коллекции, id которого равен заданному" +
                                "\nsave: сохранить коллекцию в файл" +
                                "\nsave_and_exit: сохранить изменения и выйти из порграммы" +
                                "\nfilter_greater_than_price: вывести элементы, значение поля price которых больше заданного" +
                                "\ncount_less_than_price: вывести количество элементов, значение поля price которых меньше заданного" +
                                "\ncount_greater_than_owner: вывести количество элементов, значение поля owner которых больше заданного" +
                                "\nadd_if_max: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции" +
                                "\nremove_greater: удалить из коллекции все элементы, превышающие заданный" +

                                "\nexit: завершить работу программы");
                        break;
                    case ("remove_greater"):
                        ForExecuteScript.remove_greater(pq, file1);
                        break;
                    case ("add_if_max"):
                        ForExecuteScript.add_if_max(pq, file1);
                        break;
                    case ("filter_greater_than_price"):
                        try {
                            ForExecuteScript.filter_greater_than_price(pq, file1);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("count_less_than_price"):
                        try {
                            ForExecuteScript.count_less_than_price(pq, file1);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("clear"):
                        Comands.clear(pq);
                        System.out.println("Все элементы успешно удалены");

                        break;
                    case ("remove_by_id"):
                        try {
                            Comands.remove_by_id(pq);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("update_id"):
                        try {
                            Comands.update_id(pq);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("exit"):
                        exit = false;
                        break;
                    case ("execute_script"):
                        CheckForLoops.compare(pq);
                        break;
                }
                line = reader.readLine();
            }
        } catch (NullPointerException ignored) {

        } catch (FileNotFoundException e) {
            System.err.println("Не получается найти ваш файл");
            System.err.println("Проверьте правильность написания пути к файлу");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}