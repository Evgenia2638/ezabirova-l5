package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;
/**
 * Класс, содержащий команды
 */
public abstract class Comands {
    private static Date initDate = new Date();
    static int x = 0;

    public static void remove_by_id(PriorityQueue<Product> pq) {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите id элемента, чтобы удалить его");
        String str1 = Scanner.nextLine();
        if (str1 == null) {
            System.err.println("Значение id не может быть пустым");
            System.out.println("Попробуйте запустить команду remove_by_id заново");
        } else {
            Long id2 = Long.valueOf(str1);
            Object[] products = pq.toArray();
            for (Object o : products) {
                Product product = (Product) o;
                if (product.getId().equals(id2)) {
                    pq.remove(product);
                    h++;
                }
                if (h == 0) {
                    System.out.println("К сожалению, элемента с таким id  в коллекции нет");
                    System.out.println("Попробуйте запустить команду remove_by_id заново");
                } else {
                    System.out.println("Элемент успешно удален");
                }
            }
        }
    }
    public static void update_id(PriorityQueue<Product> pq) {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите id элемента, чтобы обновить его значение");
        String str1 = Scanner.nextLine();
        Long id2 = Long.valueOf(str1);
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getId().equals(id2)) {
                pq.remove(product);

                Coordinates coordinates = new Coordinates();
                Person person = new Person();
                Scanner scanner = new Scanner(System.in);

                try {
                    System.out.println("Введите имя");
                    String name = scanner.nextLine();
                    product.setName(name);
                    person.setName(name);
                } catch (Exception e) {

                    System.err.println("Скорее всего вы некорректно ввели имя");
                    System.out.println("Попробуйте ввести имя элемента заново");
                    try {
                        String name = scanner.nextLine();
                        person.setName(name);
                        product.setName(name);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Координату X");
                    coordinates.setX(Double.parseDouble(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы некорректно ввели значение Х");
                    System.out.println("Попробуйте заново ввести значение Х");
                    try {
                        coordinates.setX(Double.parseDouble(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Координату Y");
                    coordinates.setY(Long.parseLong(scanner.nextLine()));
                    product.setCoordinates(coordinates);
                } catch (Exception e) {
                    System.err.println("Скорее всего вы некорректно ввели значение Y");
                    System.out.println("Попробуйте заново ввести значение Y");
                    try {
                        coordinates.setY(Long.parseLong(scanner.nextLine()));
                        product.setCoordinates(coordinates);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите вес");
                    person.setWeight((int) Double.parseDouble(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы ввели некорректное значение веса");
                    System.out.println("Попробуйте ввести вес заново");
                    try {
                        person.setWeight((int) Double.parseDouble(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите цену(число)");
                    product.setPrice(Float.parseFloat(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы некорректно ввели цену");
                    System.out.println("Попробуйте ввести заново значение цены");
                    try {
                        product.setPrice(Float.parseFloat(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите цвет глаз");
                    System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
                    person.setEyeColor(Color.valueOf(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы ввели некорректное значение цвета глаза");
                    System.out.println("Попробуйте ввсети цвет глаз заново");
                    try {
                        person.setEyeColor(Color.valueOf(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите цвет волос");
                    System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
                    person.setHairColor(Color.valueOf(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы ввели некорректное значение цвета волос");
                    System.out.println("Попробуйте ввести цвет волос заново");
                    try {
                        person.setHairColor(Color.valueOf(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите страну рождения");
                    System.out.println("RUSSIA, SPAIN, VATICAN, ITALY");
                    person.setNationality(Country.valueOf(scanner.nextLine()));
                    product.setOwner(person);
                } catch (Exception e) {
                    System.err.println("Скорее всего вы ввели страну рождения");
                    System.out.println("Попробуйте заново ввести значение страны рождения");
                    try {
                        person.setNationality(Country.valueOf(scanner.nextLine()));
                        product.setOwner(person);
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                try {
                    System.out.println("Введите единицу изменения(enum)");
                    System.out.println("KILOGRAMS, METERS, CENTIMETERS, LITERS");
                    product.setUnitOfMeasure(UnitOfMeasure.valueOf(scanner.nextLine()));
                } catch (Exception e) {
                    System.err.println("Скорее всего вы некорректно ввели единицу измерения");
                    System.out.println("Попробуйте заново ввести единицу измерения");
                    try {
                        product.setUnitOfMeasure(UnitOfMeasure.valueOf(scanner.nextLine()));
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                }
                product.setCreationDate(new Date());
                product.setId(id2);

                pq.add(product);
            } else {
                h++;
            }
        }
        if (h == pq.size()) {
            System.out.println("К сожалению, элемента с таким id  в коллекции нет");
            System.out.println("Попробуйте запустить команду update_id заново");
        } else {
            System.out.println("Элемент успешно обновлен");
        }
    }
    public static void show(PriorityQueue<Product> pq) {
        Object[] products = pq.toArray();
        for (Object product : products) {
            System.out.println(product);
        }
    }
    public static void remove_first(PriorityQueue<Product> pq) {
        pq.remove();
    }
    public static void info(PriorityQueue<Product> pq) {
        System.out.println("тип " + pq.getClass().getSimpleName());
        System.out.println("количество элементов " + pq.size());
        System.out.println("дата инициализации " + initDate.toString());

    }
    public static void clear(PriorityQueue<Product> pq) {

        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            pq.remove(o);
        }
    }
    public static void add(PriorityQueue<Product> pq) {
        Product product = new Product();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        Scanner scanner = new Scanner(System.in);
        try {
            System.out.println("Введите имя элемента");
            String name = scanner.nextLine();
            product.setName(name);
            person.setName(name);
        } catch (Exception e) {
            System.out.println("Скорее всего вы ввели некорректное значение имени");
            System.out.println("Попробуйте ввести имя заново");
            try {
                String name = scanner.nextLine();
                product.setName(name);
                person.setName(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Координату X");
            coordinates.setX(Double.parseDouble(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели значение Х");
            System.out.println("Попробуйте заново ввести значение Х");
            try {
                coordinates.setX(Double.parseDouble(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Координату Y");
            coordinates.setY(Long.parseLong(scanner.nextLine()));
            product.setCoordinates(coordinates);
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели значение Y");
            System.out.println("Попробуйте заново ввести значение Y");
            try {
                coordinates.setY(Long.parseLong(scanner.nextLine()));
                product.setCoordinates(coordinates);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите вес");
            person.setWeight((int) Double.parseDouble(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение веса");
            System.out.println("Попробуйте ввести вес заново");
            try {
                person.setWeight((int) Double.parseDouble(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите цену элемента");
            product.setPrice(Float.parseFloat(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели цену");
            System.out.println("Попробуйте ввести заново значение цены");
            try {
                product.setPrice(Float.parseFloat(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите цвет глаз");
            System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
            person.setEyeColor(Color.valueOf(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение цвета глаза");
            System.out.println("Попробуйте ввсети цвет глаз заново");
            try {
                person.setEyeColor(Color.valueOf(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите цвет волос");
            System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
            person.setHairColor(Color.valueOf(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение цвета волос");
            System.out.println("Попробуйте ввести цвет волос заново");
            try {
                person.setHairColor(Color.valueOf(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите страну рождения");
            System.out.println("RUSSIA, SPAIN, VATICAN, ITALY");
            person.setNationality(Country.valueOf(scanner.nextLine()));
            product.setOwner(person);
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели страну рождения");
            System.out.println("Попробуйте заново ввести значение страны рождения");
            try {
                person.setNationality(Country.valueOf(scanner.nextLine()));
                product.setOwner(person);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите единицу изменения");
            System.out.println("KILOGRAMS, METERS, CENTIMETERS, LITERS");
            product.setUnitOfMeasure(UnitOfMeasure.valueOf(scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели единицу измерения");
            System.out.println("Попробуйте заново ввести единицу измерения");
            try {
                product.setUnitOfMeasure(UnitOfMeasure.valueOf(scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        product.setCreationDate(new Date());
        long[] id = new long[1];
        product.setId((long) id.hashCode());
        int h = 0;
        pq.add(product);
    }
    public static void count_less_than_price(PriorityQueue<Product> pq) {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите price для сравнения с элементами коллекции");
        String str1 = Scanner.nextLine();
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
    public static void filter_greater_than_price(PriorityQueue<Product> pq) {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите price для сравнения с элементами коллекции");
        String str1 = Scanner.nextLine();
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
    public static void add_if_max(PriorityQueue<Product> pq) throws Exception {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        float price2 = 0;
        Object[] products = pq.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() > price2) {
                price2 = product.getPrice();
            }
        }
        System.out.println("Введите цену элемента");
        String str2 = Scanner.nextLine();
        float price1 = Float.parseFloat(str2);
        Product product = new Product();
        Coordinates coordinates = new Coordinates();
        Person person = new Person();
        try {
            System.out.println("Введите имя элемента");
            String name = Scanner.nextLine();
            product.setName(name);
            person.setName(name);
        } catch (Exception e) {

            System.err.println("Скорее всего вы некорректно ввели имя");
            System.out.println("Попробуйте ввести имя элемента заново");
            try {
                String name = Scanner.nextLine();
                person.setName(name);
                product.setName(name);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Координату X");
            coordinates.setX(Double.parseDouble(Scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели значение Х");
            System.out.println("Попробуйте заново ввести значение Х");
            try {
                coordinates.setX(Double.parseDouble(Scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Координату Y");
            coordinates.setY(Long.parseLong(Scanner.nextLine()));
            product.setCoordinates(coordinates);
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели значение Y");
            System.out.println("Попробуйте заново ввести значение Y");
            try {
                coordinates.setY(Long.parseLong(Scanner.nextLine()));
                product.setCoordinates(coordinates);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите вес");
            person.setWeight((int) Double.parseDouble(Scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение веса");
            System.out.println("Попробуйте ввести вес заново");
            try {
                person.setWeight((int) Double.parseDouble(Scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите цвет глаз");
            System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
            person.setEyeColor(Color.valueOf(Scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение цвета глаза");
            System.out.println("Попробуйте ввсети цвет глаз заново");
            try {
                person.setEyeColor(Color.valueOf(Scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите цвет волос");
            System.out.println(" RED, BLACK, BLUE, YELLOW, WHITE, ORANGE");
            person.setHairColor(Color.valueOf(Scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели некорректное значение цвета волос");
            System.out.println("Попробуйте ввести цвет волос заново");
            try {
                person.setHairColor(Color.valueOf(Scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите страну рождения");
            System.out.println("RUSSIA, SPAIN, VATICAN, ITALY");
            person.setNationality(Country.valueOf(Scanner.nextLine()));
            product.setOwner(person);
        } catch (Exception e) {
            System.err.println("Скорее всего вы ввели страну рождения");
            System.out.println("Попробуйте заново ввести значение страны рождения");
            try {
                person.setNationality(Country.valueOf(Scanner.nextLine()));
                product.setOwner(person);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        try {
            System.out.println("Введите единицу изменения(enum)");
            System.out.println("KILOGRAMS, METERS, CENTIMETERS, LITERS");
            product.setUnitOfMeasure(UnitOfMeasure.valueOf(Scanner.nextLine()));
        } catch (Exception e) {
            System.err.println("Скорее всего вы некорректно ввели единицу измерения");
            System.out.println("Попробуйте заново ввести единицу измерения");
            try {
                product.setUnitOfMeasure(UnitOfMeasure.valueOf(Scanner.nextLine()));
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
        product.setPrice(price1);
        product.setCreationDate(new Date());
        long[] id = new long[1];
        product.setId((long) Arrays.hashCode(id));
        if (price1 > price2) {
            pq.add(product);
            System.out.println("Ваш элемент успешо добавлен");
        } else {
            System.out.println("Ваш элемент не добавлен");
            System.out.println("Значение вашего элемента не превышает значение наибольшего значения из этой коллекции");
        }

    }
    public static void remove_greater(PriorityQueue<Product> pq) {
        int h = 0;
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите координату Х для сравнения");
        String X1 = Scanner.nextLine();
        System.out.println("Введите координату Y для сравнения");
        String Y1 = Scanner.nextLine();
        System.out.println("Введите price для сравнения с элементами");
        String str1 = Scanner.nextLine();
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
    public static void execute_script(PriorityQueue<Product> pq) throws IOException {
        System.out.println("Введите расположение вашего файла");
        Scanner sc = new Scanner(System.in);
        String str1 = sc.nextLine();
        File file = new File(str1);
       // try {
            CheckForLoops.add(String.valueOf(file));
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
                            ForExecuteScript.remove_greater(pq, file);
                            break;
                        case ("add_if_max"):
                            ForExecuteScript.add_if_max(pq, file);
                            break;
                        case ("filter_greater_than_price"):
                            try {
                                ForExecuteScript.filter_greater_than_price(pq, file);
                            } catch (NumberFormatException e) {
                                System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                            }
                            break;
                        case ("count_less_than_price"):
                            try {
                                ForExecuteScript.count_less_than_price(pq, file);
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
                            File file1 = new File(reader.readLine());
                          //  CheckForLoops.add(String.valueOf(file1));
                            ForExecuteScript.execute_script_for_file(pq, file1);
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