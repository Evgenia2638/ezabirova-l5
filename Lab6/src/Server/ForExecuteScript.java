package Server;

import Collection.*;
import Client.CommandsForClient;

import java.io.*;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

public class ForExecuteScript {
    static HashSet<File> WayToFiles = new HashSet<>();
    private static Date initDate = new Date();
    public static void executeScript(PriorityQueue<Product> pq, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] answer = new byte[400];
        ByteBuffer bufForAnswer = ByteBuffer.wrap(answer);
        bufForAnswer.clear();
        socket=DC.receive(bufForAnswer);
        String serverAnswer = new String(answer);
        System.out.println(serverAnswer);
        File file = new File(serverAnswer);
        System.out.println(file);
        WayToFiles.add(file);
        try {
            FileReader fr = new FileReader(file);
            Object[] products = pq.toArray();
            boolean isExit = false;
            while (!isExit) {
                BufferedReader reader = new BufferedReader(fr);
                String line = reader.readLine();
                switch (line.toLowerCase()) {
                    case ("add"):
                        ForExecuteScript.add(pq, file, DC, socket);
                        break;
                    case ("info"):
                        CommandsForClient.takerCommands("info",DC,socket);
                        break;
                    case ("show"):
                        CommandsForClient.takerCommands("show",DC,socket);
                        break;
                    case ("remove_first"):
                        CommandsForClient.takerCommands("remove_fist",DC,socket);
                        break;
                    case ("help"):
                        CommandsForClient.takerCommands("help",DC,socket);
                        break;
                    case ("remove_greater"):
                        ForExecuteScript.removeGreater(pq, file,DC, socket);
                        break;
                    case ("filter_greater_than_price"):
                        ForExecuteScript.filterGreaterThanPrice(pq, file,DC, socket);
                        break;
                    case ("clear"):
                        CommandsForClient.takerCommands("clear",DC,socket);
                        break;
                    case ("remove_by_id"):
                        ForExecuteScript.removeById(pq, file, DC, socket);
                        break;
                    case ("update_id"):
                        ForExecuteScript.updateId(pq, file, DC, socket);
                        break;
                    case ("execute_script"):
                        File file1 = new File(reader.readLine());
                        if (!WayToFiles.contains(file1)) {
                            WayToFiles.add(file1);
                            ForExecuteScript.executeScriptForFile(pq, file1,DC,socket);
                        } else {
                            WayToFiles.clear();
                            String message = "Программа не может выполнить скрипт из указанного файла \nЭто может привести к зацыкливанию";
                            CommandsForServer.sender(message,DC,socket);
                        }
                        break;
                }
                line = reader.readLine();
            }
        } catch (NullPointerException ignored) {
            String message = "Ваш файл пуст";
            CommandsForServer.sender(message,DC,socket);
        } catch (FileNotFoundException e) {
            String message = "Не получается найти ваш файл \nПроверьте правильность написания пути к файлу";
            CommandsForServer.sender(message,DC,socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private static void add(PriorityQueue<Product> pq, File file,DatagramChannel DC, SocketAddress socket ) throws IOException {
        String stroka = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        Date date = new Date();
        try {
            String line1 = reader.readLine();
            String line = reader.readLine();
            String delimeter = " ";
            if (line == null) {
                stroka ="Вы не ввели аргументы";
                CommandsForServer.sender(stroka,DC,socket);
            } else {
                String[] subStr = line.split(delimeter);
                if (Float.parseFloat(subStr[0]) <= -148) {
                    stroka = "Значение х должно быть больше -148";
                    CommandsForServer.sender(stroka,DC,socket);
                } else {
                    if (Integer.parseInt(subStr[1]) <= -550) {
                        stroka = "Значение y должно быть больше -550";
                        CommandsForServer.sender(stroka,DC,socket);
                    } else {
                        if (subStr[2] == null) {
                            stroka = "У элемента должно быть имя";
                            CommandsForServer.sender(stroka,DC,socket);
                        } else {
                            if (Float.parseFloat(subStr[3]) <= 0) {
                                stroka = "Вес должен быть больше нуля";
                                CommandsForServer.sender(stroka,DC,socket);
                            } else {
                                if (Long.parseLong(subStr[7]) <= 0) {
                                    stroka = "Цена должна быть больше нуля";
                                    CommandsForServer.sender(stroka,DC,socket);
                                } else {
                                    Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                                    Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                                    Product product = new Product(coordinates.hashCode(), subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                                    stroka = "Элемент успешно добавлен";
                                    CommandsForServer.sender(stroka,DC,socket);
                                    pq.add(product);
                                }
                            }
                        }
                    }
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            stroka ="Элемент не добавлен\nВы не ввели все данные";
            CommandsForServer.sender(stroka,DC,socket);
        }catch (NumberFormatException ignored) {
            stroka = "Элемент не добавлен \nВы ввели некорректные данные \nПопробуйте добавить элемент еще раз";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    private static void filterGreaterThanPrice(PriorityQueue<Product> pq, File file, DatagramChannel DC, SocketAddress socket) throws IOException {
        boolean flag = false;
        String stroka = "";
        BufferedReader reader = new BufferedReader(new FileReader(file));
        try {

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
                stroka ="В коллекци нет таких элментов, чтобы значение price было больше чем " + price;
                CommandsForServer.sender(stroka,DC,socket);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            stroka = "Команда не можт быть выполнена\nВы не ввели все данные";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    private static void removeGreater(PriorityQueue<Product> pq, File file, DatagramChannel DC, SocketAddress socket) throws IOException {
        boolean flag = false;
        String stroka = "";
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
               stroka = "Элементы успешно удалены";
                CommandsForServer.sender(stroka,DC,socket);
            } else {
                stroka = "В коллекции нет элементов, которые бы превышали заданный";
                CommandsForServer.sender(stroka,DC,socket);
            }
        }catch (ArrayIndexOutOfBoundsException e) {
            stroka = "Команда не выполнена \nВы не ввели все данные";
            CommandsForServer.sender(stroka,DC,socket);
        } catch (NumberFormatException ignored) {
            stroka = "Вы ввели некорректные данные \nПопробуйте добавить элемент еще раз";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    private static void removeById(PriorityQueue<Product> priorityQueue, File file, DatagramChannel DC, SocketAddress socket) throws IOException  {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String  stroka = "";
        Object[] products = priorityQueue.toArray();
        int sizeProduct = priorityQueue.size();
        try {
            String line = reader.readLine();
            String id = reader.readLine();
            if (id == null) {
                stroka = "Значение id не может быть пустым";
                CommandsForServer.sender(stroka,DC,socket);
            } else {
                int id2 = Integer.parseInt(id);
                for (Object o : products) {
                    Product product = (Product) o;
                    if (id2 == product.getId()) {
                        priorityQueue.remove(o);
                        stroka = "Элемент успешно удален";
                        CommandsForServer.sender(stroka,DC,socket);
                    }
                }
                if (priorityQueue.size() == sizeProduct) {
                    stroka = "Элемент не удален \nК сожалению, элемента с таким id  в коллекции нет";
                    CommandsForServer.sender(stroka,DC,socket);
                }
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            stroka = "Элемент не удален \nВы не ввели все данные";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    private static void updateId(PriorityQueue<Product> priorityQueue, File file, DatagramChannel DC, SocketAddress socket) throws IOException {
        String stroka = "";
        boolean flag = false;
        BufferedReader reader = new BufferedReader(new FileReader(file));
        Object[] products = priorityQueue.toArray();
        Date date = new Date();
        int sizeProduct = priorityQueue.size();
        stroka = "Введите id элемента, чтобы обновить его";
        CommandsForServer.sender(stroka,DC,socket);
        String line1 = reader.readLine();
        String id = reader.readLine();
        String delimeter = " ";
        if (id == null) {
            stroka = "Значение id не может быть пустым";
            CommandsForServer.sender(stroka,DC,socket);
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
                            stroka = "Вы не ввели аргументы";
                            CommandsForServer.sender(stroka,DC,socket);
                        } else {
                            String[] subStr = line.split(delimeter);
                            if (Float.parseFloat(subStr[0]) <= -148) {
                                stroka = "Значение х должно быть больше -148 \nЭлемент не добавлен";
                                CommandsForServer.sender(stroka,DC,socket);
                            } else {
                                if (Integer.parseInt(subStr[1]) <= -550) {
                                    stroka = "Значение у должно быть больше -550 \nЭлемент не добавлен";
                                    CommandsForServer.sender(stroka,DC,socket);
                                } else {
                                    if (subStr[2] == null) {
                                        stroka = "Имя не может быть путым \nЭлемент не добавлен";
                                        CommandsForServer.sender(stroka,DC,socket);
                                    } else {
                                        if (Float.parseFloat(subStr[3]) <= 0) {
                                            stroka = "Вес должен быть больше 0 \nЭлемент не добавлен";
                                            CommandsForServer.sender(stroka,DC,socket);
                                        } else {
                                            if (Long.parseLong(subStr[7]) <= 0) {
                                                stroka = "Цена должена быть больше \nЭлемент не добавлен";
                                                CommandsForServer.sender(stroka,DC,socket);
                                            } else {
                                                Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                                                Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                                                Product product2 = new Product(id2, subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                                                stroka = "Элемент успешно обновлен";
                                                CommandsForServer.sender(stroka,DC,socket);
                                                priorityQueue.add(product2);
                                            }

                                        }
                                    }
                                }
                            }
                        }

                    }catch (ArrayIndexOutOfBoundsException e) {
                        stroka = "Элемент не обновлен \nВы не ввели все данные";
                        CommandsForServer.sender(stroka,DC,socket);
                    } catch (NumberFormatException ignored) {
                       stroka = "Элемент не обновлен\nВы ввели некорректные данные\nПопробуйте добавить элемент еще раз";
                        CommandsForServer.sender(stroka,DC,socket);
                    }
                }
            }
        }
        if (!flag) {
            stroka = "Элемент не может быть обновлен\nВ коллекции нет элемента с таким id";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    public  static void executeScriptForFile(PriorityQueue<Product> pq, File file1, DatagramChannel DC, SocketAddress socket) throws IOException {
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
                        ForExecuteScript.add(pq, file1, DC, socket);
                        break;
                    case ("info"):
                        CommandsForServer.info(pq,DC,socket);
                        break;
                    case ("show"):
                        CommandsForServer.show(pq,DC,socket);
                        break;
                    case ("remove_first"):
                        CommandsForServer.removeFirst(pq,DC,socket);
                        break;
                    case ("help"):
                        CommandsForServer.help(DC,socket);
                        break;
                    case ("remove_greater"):
                        ForExecuteScript.removeGreater(pq, file1,DC,socket);
                        break;
                    case ("filter_greater_than_price"):
                        try {
                            ForExecuteScript.filterGreaterThanPrice(pq, file1,DC,socket);
                        } catch (NumberFormatException e) {
                            System.err.println("Вы не ввели значение price, из-за этого сравнение невозможно");
                        }
                        break;
                    case ("clear"):
                        CommandsForServer.clear(pq,DC,socket);
                        break;
                    case ("remove_by_id"):
                        try {
                            ForExecuteScript.removeById(pq, file1,DC,socket);
                        } catch (Exception e) {
                            System.out.println("Что-то пошло не так и элемент не обновлен");
                        }
                        break;
                    case ("update_id"):
                        try {
                            ForExecuteScript.updateId(pq, file1,DC,socket);
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
                            ForExecuteScript.executeScriptForFile(pq,file1,DC,socket);
                        }else {
                            WayToFiles.clear();
                            String stroka = "Программа не может выполнить скрипт из указанного файла \nЭто может привести к зацыкливанию";
                            CommandsForServer.sender(stroka,DC,socket);
                        }
                        break;
                }
                line = reader.readLine();
            }
        } catch (NullPointerException ignored) {
        }catch (ArrayIndexOutOfBoundsException e) {
            String stroka = "Введены некорректные данные";
            CommandsForServer.sender(stroka,DC,socket);
        } catch (FileNotFoundException e) {
           String stroka = "Не получается найти ваш файл \nПроверьте правильность написания пути к файлу";
            CommandsForServer.sender(stroka,DC,socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}