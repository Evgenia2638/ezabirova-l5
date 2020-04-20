package Lab5;


import java.io.File;
import java.io.IOException;
import java.util.PriorityQueue;
import java.util.Scanner;

public class Main{
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            String path = args[0];
            boolean exit = true;
            File file = new File(path);
            //File file = new File("D:\\Рабочий стол\\testcsv.csv");
            PriorityQueue<Product> productPriorityQueue = MakerColl.CSVtoCollection(file);

            while (exit) {
                System.out.println();
                System.out.println("Введите команду");
                System.out.println("Чтобы увидеть список команд наберите help");
                String num = scanner.nextLine();
                String[] word = num.split(" ");
                switch (word[0].toLowerCase()) {
                    case ("add"):
                            Comands.add(productPriorityQueue);
                            //Check.checkElement(productPriorityQueue);
                        break;
                    case ("info"):
                        Comands.info(productPriorityQueue);
                        break;
                    case ("show"):
                        //Check.checkCollection(productPriorityQueue);
                        if (productPriorityQueue.size() > 0) {
                          Comands.show(productPriorityQueue);
                        } else {
                            System.out.println("Коллекция пуста");
                            exit = false;
                        }
                        break;
                    case ("remove_first"):
                        Comands.removeFirst(productPriorityQueue);
                        System.out.println("Объект удален");
                        break;
                    case ("help"):
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
                                "\nremove_greater: удалить из коллекции все элементы, превышающие заданный"+
                                "\nexecute_script: считать и исполнить скрипт из указанного файла."+
                                "\nexit: завершить работу программы");
                        break;
                    case("remove_greater"):
                        Comands.removeGreater(productPriorityQueue);
                        break;
                    case ("add_if_max"):
                        Comands.addIfMax(productPriorityQueue);
                        break;
                    case ("filter_greater_than_price"):
                        try {
                            Comands.filterGreaterThanPrice(productPriorityQueue);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("count_less_than_price"):
                        try {
                            Comands.countLessThanPrice(productPriorityQueue);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("clear"):
                        Comands.clear(productPriorityQueue);
                        System.out.println("Все элементы успешно удалены");
                        break;
                    case ("remove_by_id"):
                        try {
                            Comands.removeById(productPriorityQueue);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("execute_script"):
                       try{
                           ForExecuteScript.executeScript(productPriorityQueue);
                     } catch (NullPointerException e){
                           System.out.println("lol");
                       }
                        break;
                    case ("update_id"):
                        try {
                            Comands.updateId(productPriorityQueue);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("save"):
                        try {
                            MakerFile.saveFile(file, productPriorityQueue);
                            System.out.println("Файл сохранен");
                        } catch (IOException e) {
                            System.err.println("Мне жаль, но неудалось сохранить файл");
                        }
                        break;
                    case ("exit"):
                        exit = false;
                        break;
                    case ("save_and_exit"):
                        try {
                            MakerFile.saveFile(file, productPriorityQueue);
                            System.out.println("Файл сохранен");
                            exit = false;
                        } catch (IOException e) {
                            System.err.println("Мне жаль, но неудалось сохранить файл");
                        }
                        break;
                    default:
                        System.out.println("Мне жаль, но команда введена не коректно");
                }
            }
        } catch (IOException e) {
            System.err.println("Что-то пошло не так и не удалось прочесть файл");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}