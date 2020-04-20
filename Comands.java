package Lab5;

import java.io.*;
import java.util.Arrays;
import java.util.Date;
import java.util.PriorityQueue;
import java.util.Scanner;

public abstract class Comands {
    private static Date initDate = new Date();
    static int x = 0;

    public static void removeById(PriorityQueue<Product> pq) {
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
    public static void updateId(PriorityQueue<Product> pq) {
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
    public static void removeFirst(PriorityQueue<Product> pq) {
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
    public static void countLessThanPrice(PriorityQueue<Product> pq) {
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
    public static void filterGreaterThanPrice(PriorityQueue<Product> pq) {
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
    public static void addIfMax(PriorityQueue<Product> pq) throws Exception {
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
    public static void removeGreater(PriorityQueue<Product> pq) {
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
}