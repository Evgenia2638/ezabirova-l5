import java.io.*;
import java.text.DateFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class Commands {
    private static Date initDate = new Date();
    public static void help() {
        System.out.println("Информация о доступных командах:" +
                "\ninfo: вывести в стандартный поток вывода информацию о коллекции (тип, дата инициализации, количество элементов и т.д.)" +
                "\nadd: добавить новый элемент в коллекцию" +
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
                "\nexecute_script: считать и исполнить скрипт из указанного файла." +
                "\nexit: завершить работу программы");
    }
    public static void info(PriorityQueue<Product> priorityQueue) {
        System.out.println("тип " + priorityQueue.getClass().getSimpleName());
        System.out.println("количество элементов " + priorityQueue.size());
        System.out.println("дата инициализации " + initDate.toString());

    }
    public static void show(PriorityQueue<Product> priorityQueue) {
        if (priorityQueue.size() == 0) {
            System.out.println("Коллекция пуста");
        } else {
            Object[] products = priorityQueue.toArray();
            for (Object product : products) {
                System.out.println(product);
            }
        }
    }
    private static void add(PriorityQueue<Product> priorityQueue) throws ParseException {
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = new Date();
        System.out.println("Введите данные элемента в таком формате:");
        System.out.println("X, Y, Name, Weight, Eye_Color, Hair_Color, Country, Price, Unit_Of_Measure");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            String delimeter = ", ";
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
                                    Product product = new Product(coordinates.hashCode(), subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                                    System.out.println("Элемент успешно добавлен");
                                    priorityQueue.add(product);
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
    public static void clear(PriorityQueue<Product> priorityQueue) {
        if (priorityQueue.size() == 0) {
            System.out.println("Ваша коллекция уже пуста");
        } else {
            priorityQueue.clear();
            System.out.println("Ваша коллекция теперь пуста");
        }
    }
    public static void removeFirst(PriorityQueue<Product> priorityQueue) {
        if (priorityQueue.size() > 1) {
            priorityQueue.remove();
            System.out.println("Элемент успешно удален");
        } else {
            if (priorityQueue.size() == 0) {
                System.out.println("Ваша коллекция пуста и удалить элемент нельзя");
            }
            if (priorityQueue.size() == 1) {
                System.out.println("Вы успешно удалили крайний элемент");
                System.out.println("Теперь ваша коллекция пуста");
            }
        }
    }
    private static void removeById(PriorityQueue<Product> priorityQueue) {
        Scanner Scanner = new Scanner(System.in);
        Object[] products = priorityQueue.toArray();
        int sizeProduct = priorityQueue.size();
        System.out.println("Введите id элемента, чтобы удалить его");
        String id = Scanner.nextLine();
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
    private static void updateId(PriorityQueue<Product> priorityQueue) {
        boolean flag = false;
        Scanner Scanner = new Scanner(System.in);
        Object[] products = priorityQueue.toArray();
        Date date = new Date();
        int sizeProduct = priorityQueue.size();
        System.out.println("Введите id элемента, чтобы обновить его");
        String id = Scanner.nextLine();
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
                    System.out.println("Введите данные элемента в таком формате:");
                    System.out.println("X, Y, Name, Weight, Eye_Color, Hair_Color, Country, Price, Unit_Of_Measure");
                    String line = Scanner.nextLine();
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
    private static void save(PriorityQueue<Product> priorityQueue, File file) throws IOException, ParseException {
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file));
        Object[] products = priorityQueue.toArray();
        Format format = new SimpleDateFormat("dd.MM.yyyy");
        String empty = " ";
        int param;
        String stroka;
        for (Object o : products) {
            Product product = (Product) o;
            param = (int) product.getCoordinates().getX();
            stroka = String.valueOf(param)+ " ";
            bufferedOutputStream.write(stroka.getBytes());
            // bufferedOutputStream.write(44);

            stroka = String.valueOf(product.getCoordinates().getY()) + " ";
            bufferedOutputStream.write(stroka.getBytes());
            // bufferedOutputStream.write(44);

            stroka= product.getName()+" ";
            bufferedOutputStream.write(stroka.getBytes());
            // bufferedOutputStream.write(44);

            param = (int)product.getOwner().getWeight()  ;
            stroka = param + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka = product.getOwner().getEyeColor() + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka = product.getOwner().getHairColor() + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka = product.getOwner().getNationality() + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka = String.valueOf(product.getId())+ " ";
            bufferedOutputStream.write(stroka.getBytes());
            //bufferedOutputStream.write(44);

            stroka = format.format(product.getCreationDate()) + " ";
            bufferedOutputStream.write(stroka.getBytes());

            param = (int)product.getPrice();
            stroka =  param + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka = product.getUnitOfMeasure() + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka= "\n";
            bufferedOutputStream.write(stroka.getBytes());

        }
        bufferedOutputStream.close();
        System.out.println("Элементы успешно сохранены в коллекцию");
}
    private static void filterGreaterThanPrice(PriorityQueue<Product> priorityQueue) {
        boolean flag = false;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите price для сравнения с элементами коллекции");
        String stroka = Scanner.nextLine();
        float price = Float.parseFloat(stroka);
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() > price){
                System.out.println(product);
                flag = true;
            }
        }
        if (!flag){
            System.out.println("В коллекци нет таких элментов, чтобы значение price было больше чем "+ price);
        }
    }
    private static void countLessThanPrice(PriorityQueue<Product> priorityQueue) {
        boolean flag = false;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите price для сравнения с элементами коллекции");
        String stroka = Scanner.nextLine();
        float price = Float.parseFloat(stroka);
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() < price){
                System.out.println(product);
                flag = true;
            }
        }
        if (!flag){
            System.out.println("В коллекци нет таких элментов, чтобы значение price было меньше чем "+ price);
        }
    }
    private static void countGreaterThanOwner(PriorityQueue<Product> priorityQueue){
        boolean flag = false;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите weight для сравнения с элементами коллекции");
        String stroka = Scanner.nextLine();
        float weight = Float.parseFloat(stroka);
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getOwner().getWeight() < weight){
                System.out.println(product);
                flag = true;
            }
        }
        if (!flag){
            System.out.println("В коллекци нет таких элментов, чтобы значение owner было больше чем "+ weight);
        }
    }
    private static void removeGreater(PriorityQueue<Product> priorityQueue) {
     boolean flag = false;
        System.out.println("Введите данные элемента в таком формате:");
        System.out.println("X, Y, Weight, Price");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            String delimeter = ", ";
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                String[] subStr = line.split(delimeter);
                Object[] products = priorityQueue.toArray();
                for (Object o : products) {
                    Product product = (Product) o;
                    if (product.getCoordinates().getX() < Float.parseFloat(subStr[0])) {
                        if (product.getCoordinates().getX() <= Integer.parseInt(subStr[1])) {
                            if (product.getOwner().getWeight() <= Float.parseFloat(subStr[2])) {
                                if (product.getPrice() <= (Long.parseLong(subStr[3]))) {
                                    priorityQueue.remove(o);
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
    private static void addIfMax(PriorityQueue<Product> priorityQueue) throws ParseException{
     int parametr = 0;
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = new Date();
        System.out.println("Введите данные элемента в таком формате:");
        System.out.println("X, Y, Name, Weight, Eye_Color, Hair_Color, Country, Price, Unit_Of_Measure");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            String delimeter = ", ";
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                String[] subStr = line.split(delimeter);
                Object[] products = priorityQueue.toArray();
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
                if (parametr == priorityQueue.size()) {
                    Coordinates coordinates1 = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                    Person person1 = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                    Product product1 = new Product(coordinates1.hashCode(), subStr[2], coordinates1, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person1);
                    System.out.println("Элемент успешно добавлен");
                    priorityQueue.add(product1);
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

    /**
     *
     * @param command
     * @param productPriorityQueue
     * @param file
     * @throws Exception
     */
    public static void takerCommands(String command, PriorityQueue<Product> productPriorityQueue, File file) throws Exception {
        Date date = new Date();
        String[] commandAndArgs = command.split(" ");
        switch (commandAndArgs[0]) {

            case "help":
                help();
                break;
            case "info":
                info(productPriorityQueue);
                break;
            case "show":
                show(productPriorityQueue);
                break;
            case "exit":
                System.exit(0);
            case "add":
                    add(productPriorityQueue);
                break;
            case "clear":
                clear(productPriorityQueue);
                break;
            case "remove_first":
               removeFirst(productPriorityQueue);
                break;
            case "remove_by_id":
                removeById(productPriorityQueue);
                break;
            case "update_id":
                updateId(productPriorityQueue);
                break;
            case "save":
                save(productPriorityQueue, file);
                break;
            case "save_and_exit":
                save(productPriorityQueue, file);
                System.exit(0);
            case "filter_greater_than_price":
                filterGreaterThanPrice(productPriorityQueue);
                break;
            case "count_less_than_price":
                countLessThanPrice(productPriorityQueue);
                break;
            case "count_greater_than_owner":
                countGreaterThanOwner(productPriorityQueue);
                break;
            case "add_if_max":
                addIfMax(productPriorityQueue);
                break;
            case "remove_greater":
                removeGreater(productPriorityQueue);
                break;
            case "execute_script":
                ForExecuteScript.executeScript(productPriorityQueue);
                break;
            default:
                System.err.println("Некоректная команда");
                System.err.println("Чтобы увидеть список доступных команд наберите help");
        }
    }
}