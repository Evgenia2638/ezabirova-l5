import java.io.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForExecuteScript {
    static HashSet<File> WayToFiles = new HashSet<>();
    private static Date initDate = new Date();
    public static void executeScript(PriorityQueue<Product> pq) throws IOException {
        System.out.println("Введите расположение вашего файла");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        File file = new File(str1);
        WayToFiles.add(file);
        try {
            FileReader fr = new FileReader(file);
            Object[] products = pq.toArray();
            boolean exit = true;
            while (exit) {
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                switch (line.toLowerCase()) {
                    case ("add"):
                        ForExecuteScript.add(pq, file);
                        break;
                    case ("info"):
                        Commands.info(pq);
                        break;
                    case ("show"):
                        Commands.show(pq);
                        break;
                    case ("remove_first"):
                        Commands.removeFirst(pq);
                        break;
                    case ("help"):
                        Commands.help();
                        break;
                    case ("remove_greater"):
                        ForExecuteScript.removeGreater(pq, file);
                        break;
                    case ("add_if_max"):
                        ForExecuteScript.addIfMax(pq, file);
                        break;
                    case ("filter_greater_than_price"):
                            ForExecuteScript.filterGreaterThanPrice(pq, file);
                        break;
                    case ("clear"):
                        Commands.takerCommands("clear", pq, file);
                        break;
                    case ("remove_by_id"):
                        ForExecuteScript.removeById(pq, file);
                        break;
                    case ("update_id"):
                        ForExecuteScript.updateId(pq, file);
                        break;
                    case ("exit"):
                        exit = false;
                        break;
                    case ("execute_script"):
                        File file1 = new File(reader.readLine());
                        if (!WayToFiles.contains(file1)) {
                            WayToFiles.add(file1);
                            ForExecuteScript.executeScriptForFile(pq, file1);
                        } else {
                            WayToFiles.clear();
                            System.err.println("Программа не может выполнить скрипт из указанного файла");
                            System.err.println("Это может привести к зацыкливанию");
                        }
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
    private static void add(PriorityQueue<Product> pq, File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = new Date();
        try {
            String line1 = reader.readLine();
            String line = reader.readLine();
            String delimeter = ", ";
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                String[] subStr = line.split(delimeter);
                if (Float.parseFloat(subStr[0]) <= -148) {
                } else {
                    if (Integer.parseInt(subStr[1]) <= -550) {
                    } else {
                        if (subStr[2] == null) {
                        } else {
                            if (Float.parseFloat(subStr[3]) <= 0) {
                            } else {
                                if (Long.parseLong(subStr[7]) <= 0) {
                                } else {
                                    Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                                    Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                                    Product product = new Product(coordinates.hashCode(), subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                                    System.out.println("Элемент успешно добавлен");
                                    pq.add(product);
                                }
                            }
                        }
                    }
                }
            }
        } catch (NumberFormatException ignored) {
            System.out.println("Элемент не добавлен");
            System.out.println("Вы ввели некорректные данные");
            System.out.println("Попробуйте добавить элемент еще раз");
        }
    }
    private static void addIfMax(PriorityQueue<Product> pq, File file) throws Exception {
        int parametr = 0;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = new Date();
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String line1 = reader.readLine();
            String line = reader.readLine();
            String delimeter = ", ";
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                String[] subStr = line.split(delimeter);
                Object[] products = pq.toArray();
                for (Object o : products) {
                    Product product = (Product) o;
                    if (product.getCoordinates().getX() <= Float.parseFloat(subStr[0])) {
                        if (product.getCoordinates().getX() <= Integer.parseInt(subStr[1])) {
                            if (subStr[2] != null) {
                                if (product.getOwner().getWeight() <= Float.parseFloat(subStr[3])) {
                                    if (product.getPrice() <= (Long.parseLong(subStr[7]))) {
                                        parametr++;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (line != null) {
                String[] subStr = line.split(delimeter);
                if (parametr == pq.size()) {
                    Coordinates coordinates1 = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                    Person person1 = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                    Product product1 = new Product(coordinates1.hashCode(), subStr[2], coordinates1, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person1);
                    System.out.println("Элемент успешно добавлен");
                    pq.add(product1);
                } else {
                    System.out.println("Элемент не добавлен");
                    System.out.println("Элемент не превышает все элементы коллекции");
                }
            }
        } catch (NumberFormatException ignored) {
            System.out.println("Элемент не добавлен");
            System.out.println("Вы ввели некорректные данные");
            System.out.println("Попробуйте добавить элемент еще раз");
        }
    }
    private static void filterGreaterThanPrice(PriorityQueue<Product> pq, File file) throws IOException {
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line1 = reader.readLine();
        String line = reader.readLine();
        float price = Float.parseFloat(line);
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() > price) {
                System.out.println(product);
                flag = true;
            }
        }
        if (!flag) {
            System.out.println("В коллекци нет таких элментов, чтобы значение price было больше чем " + price);
        }
    }
    private static void removeGreater(PriorityQueue<Product> pq, File file) throws IOException {
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {
            String line1 = reader.readLine();
            String line = reader.readLine();
            String delimeter = ", ";
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                String[] subStr = line.split(delimeter);
                Object[] products = pq.toArray();
                for (Object o : products) {
                    Product product = (Product) o;
                    if (product.getCoordinates().getX() < Float.parseFloat(subStr[0])) {
                        if (product.getCoordinates().getX() <= Integer.parseInt(subStr[1])) {
                            if (product.getOwner().getWeight() <= Float.parseFloat(subStr[2])) {
                                if (product.getPrice() <= (Long.parseLong(subStr[3]))) {
                                    pq.remove(o);
                                    flag = true;
                                }
                            }
                        }
                    }
                }
            }
            if (flag) {
                System.out.println("Элементы успешно удалены");
            } else {
                System.out.println("В коллекции нет элементов, которые бы превышали заданный");
            }
        } catch (NumberFormatException ignored) {
            System.err.println("Вы ввели некорректные данные");
            System.out.println("Попробуйте добавить элемент еще раз");
        }
    }
    private static void removeById(PriorityQueue<Product> priorityQueue, File file) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Object[] products = priorityQueue.toArray();
        int sizeProduct = priorityQueue.size();
        String line = reader.readLine();
        String id = reader.readLine();
        if (id == null) {
            System.err.println("Значение id не может быть пустым");
        } else {
            int id2 = Integer.parseInt(id);
            for (Object o : products) {
                Product product = (Product) o;
                if (id2 == product.getId()) {
                    priorityQueue.remove(o);
                    System.out.println("Элемент успешно удален");
                }
            }
            if (priorityQueue.size() == sizeProduct) {
                System.out.println("Элемент не удален");
                System.out.println("К сожалению, элемента с таким id  в коллекции нет");
            }
        }
    }
    private static void updateId(PriorityQueue<Product> priorityQueue, File file) throws IOException {
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Object[] products = priorityQueue.toArray();
        Date date = new Date();
        int sizeProduct = priorityQueue.size();
        System.out.println("Введите id элемента, чтобы обновить его");
        String line1 = reader.readLine();
        String id = reader.readLine();
        String delimeter = ", ";
        if (id == null) {
            System.err.println("Значение id не может быть пустым");
        } else {
            int id2 = Integer.parseInt(id);
            for (Object o : products) {
                Product product = (Product) o;
                if (id2 == product.getId()) {
                    priorityQueue.remove(o);
                    flag = true;
                    String line = reader.readLine();
                    try {
                        if (line == null) {
                            System.out.println("Вы не ввели аргументы");
                        } else {
                            String[] subStr = line.split(delimeter);
                            if (Float.parseFloat(subStr[0]) <= -148) {
                                System.err.println("Значение х должно быть больше -148");
                                System.out.println("Элемент не добавлен");
                            } else {
                                if (Integer.parseInt(subStr[1]) <= -550) {
                                    System.err.println("Значение у должно быть больше -550");
                                    System.out.println("Элемент не добавлен");
                                } else {
                                    if (subStr[2] == null) {
                                        System.err.println("Имя не может быть путым");
                                        System.out.println("Элемент не добавлен");
                                    } else {
                                        if (Float.parseFloat(subStr[3]) <= 0) {
                                            System.err.println("Вес должен быть больше 0");
                                            System.out.println("Элемент не добавлен");
                                        } else {
                                            if (Long.parseLong(subStr[7]) <= 0) {
                                                System.err.println("Цена должена быть больше 0");
                                                System.out.println("Элемент не добавлен");
                                            } else {
                                                Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                                                Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                                                Product product2 = new Product(id2, subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                                                System.out.println("Элемент успешно обновлен");
                                                priorityQueue.add(product2);
                                            }

                                        }
                                    }
                                }
                            }
                        }
                    } catch (NumberFormatException ignored) {
                        System.out.println("Элемент не обновлен");
                        System.out.println("Вы ввели некорректные данные");
                        System.out.println("Попробуйте добавить элемент еще раз");
                    }
                }
            }
        }
        if (!flag) {
            System.err.println("Элемент не может быть обновлен");
            System.out.println("В коллекции нет элемента с таким id");
        }
    }
    public static void executeScriptForFile(PriorityQueue<Product> pq, File file1) throws IOException {
        FileReader fr1 = new FileReader(file1);
        BufferedReader reader1 = new BufferedReader(fr1);
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
                        break;
                    case ("info"):
                        Commands.info(pq);
                        break;
                    case ("show"):
                            Commands.show(pq);
                        break;
                    case ("remove_first"):
                        Commands.removeFirst(pq);
                        break;
                    case ("help"):
                       Commands.help();
                        break;
                    case ("remove_greater"):
                        ForExecuteScript.removeGreater(pq, file1);
                        break;
                    case ("add_if_max"):
                        ForExecuteScript.addIfMax(pq, file1);
                        break;
                    case ("filter_greater_than_price"):
                        try {
                            ForExecuteScript.filterGreaterThanPrice(pq, file1);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("clear"):
                        Commands.clear(pq);
                        break;
                    case ("remove_by_id"):
                        try {
                            ForExecuteScript.removeById(pq, file1);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("update_id"):
                        try {
                            ForExecuteScript.updateId(pq, file1);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("exit"):
                        exit = false;
                        break;
                    case ("execute_script"):
                        if(!WayToFiles.contains(file1)) {
                            WayToFiles.add(file1);
                            ForExecuteScript.executeScriptForFile(pq,file1);
                        }else {
                            WayToFiles.clear();
                            System.err.println("Программа не может выполнить скрипт из указанного файла");
                            System.err.println("Это может привести к зацыкливанию");
                        }
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