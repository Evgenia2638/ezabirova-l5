package Server;


import Collection.*;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.Format;
import java.util.Date;


import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

public class CommandsForServer {
    private static Date initDate = new Date();

    public static void help(DatagramChannel DC, SocketAddress socket) throws IOException {
        String stroka = "Информация о доступных командах:" +
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
                "\nremove_greater: удалить из коллекции все элементы, превышающие заданный" +
                "\nexecute_script: считать и исполнить скрипт из указанного файла" +
                "\nexit: завершить работу программы";
        CommandsForServer.sender(stroka, DC, socket);
    }
    public static void info(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        String stroka = "тип " + (priorityQueue.getClass().getSimpleName()) + "\nколичество элементов " + (priorityQueue.size()) + "\nдата инициализации " + (initDate.toString());
        CommandsForServer.sender(stroka, DC, socket);
    }
    public static void show(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        if (priorityQueue.size() == 0) {
            String stroka = "Коллекция пуста";
            CommandsForServer.sender(stroka, DC, socket);
        } else {
            String stroka = (priorityQueue.toString());
            CommandsForServer.sender(stroka, DC, socket);
        }
    }
    private static void add(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws ParseException, IOException {
        try {
            DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
            Date date = new Date();
            byte[] arguments = new byte[140];
            ByteBuffer bufAdd = ByteBuffer.wrap(arguments);
            bufAdd.clear();
            socket = DC.receive(bufAdd);
            char[] array = (new String(arguments)).toCharArray();
            String line = "";
            for (int j = 0; j < 140; j++) {
                line = line + array[j];
            }
            for (int j = 0; j < 140; j++) {
                arguments[j] = 0;
            }
            String delimeter = " ";
            String[] subStr = line.split(delimeter);
            Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
            Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
            Product product = new Product(coordinates.hashCode(), subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
            String strokaYaY = "Элемент успешно добавлен....";
            priorityQueue.add(product);
            bufAdd.flip();
            CommandsForServer.sender(strokaYaY, DC, socket);
        } catch (IllegalArgumentException e) {
            String strokaYaY = "Вы неккоректно ввели данные \nЭлемент не добавлен";
            CommandsForServer.sender(strokaYaY, DC, socket);
        }
    }
    public static void clear(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        if (priorityQueue.size() == 0) {
            String stroka = "Ваша коллекция уже пуста";
            CommandsForServer.sender(stroka, DC, socket);
        } else {
            priorityQueue.clear();
            String stroka = "Ваша коллекция теперь пуста";
            CommandsForServer.sender(stroka, DC, socket);
        }
    }
    public static void removeFirst(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {  ///+++
        if (priorityQueue.size() > 1) {
            priorityQueue.remove();
            String stroka = "Элемент успешно удален";
            CommandsForServer.sender(stroka, DC, socket);
        } else {
            if (priorityQueue.size() == 0) {
                String stroka = "Ваша коллекция пуста и удалить элемент нельзя";
                CommandsForServer.sender(stroka, DC, socket);
            }
            if (priorityQueue.size() == 1) {
                String stroka = "Вы успешно удалили крайний элемент" + "\nТеперь ваша коллекция пуста";
                CommandsForServer.sender(stroka, DC, socket);
            }
        }
    }
    private static void removeById(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] arguments = new byte[40];
        ByteBuffer bufForId = ByteBuffer.wrap(arguments);
        bufForId.clear();
        socket = DC.receive(bufForId);
        bufForId.flip();
        char[] array = (new String(arguments)).toCharArray();
        String id = "";
        for (int j = 0; j < 40; j++) {
            if (array[j] != 0) {
                id = id + array[j];
            }
        }
        for (int j = 0; j < 40; j++) {
            arguments[j] = 0;
        }
        Object[] products = priorityQueue.toArray();
        int sizeProduct = priorityQueue.size();
        int id2 = Integer.parseInt(id);
        for (Object o : products) {
            Product product = (Product) o;
            if (id2 == product.getId()) {
                priorityQueue.remove(o);
            }
        }
        if (priorityQueue.size() == sizeProduct) {
            String stroka = "Элемент не удален \nК сожалению, элемента с таким id  в коллекции нет";
            CommandsForServer.sender(stroka, DC, socket);
        } else {
            String stroka = "Элемент успешно удален";
            CommandsForServer.sender(stroka, DC, socket);
        }
    }
    private static void updateId(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        String flag = "0";
        Object[] products = priorityQueue.toArray();
        int sizeProduct = priorityQueue.size();
        byte[] argument = new byte[140];
        byte[] id = new byte[140];
        byte[] stroka = new byte[200];

        ByteBuffer bufId = ByteBuffer.wrap(id);
        bufId.clear();
        socket = DC.receive(bufId);
        char[] array = (new String(id)).toCharArray();
        String strId = "";
        for (int j = 0; j < 140; j++) {
            if (array[j] != 0) {
                strId = strId + array[j];
            }
        }
        for (Object o : products) {
            Product product = (Product) o;
            if (Integer.parseInt(strId) == product.getId()) {
                priorityQueue.remove(o);
                flag = "1";

            }
        }
        byte[] answer = flag.getBytes();
        ByteBuffer bufForAnswer = ByteBuffer.wrap(answer);
        bufForAnswer.clear();
        DC.send(bufForAnswer, socket);
        if (flag.equals("1")) {
            try {
                DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
                Date date = new Date();
                byte[] arguments = new byte[140];
                ByteBuffer bufAdd = ByteBuffer.wrap(arguments);
                bufAdd.clear();
                socket = DC.receive(bufAdd);
                char[] array1 = (new String(arguments)).toCharArray();
                String line = "";
                for (int j = 0; j < 140; j++) {
                    line = line + array1[j];
                }
                for (int j = 0; j < 140; j++) {
                    arguments[j] = 0;
                }
                String delimeter = " ";
                String[] subStr = line.split(delimeter);
                Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                Product product = new Product(Integer.parseInt(strId), subStr[2], coordinates, date, Long.parseLong(subStr[7]), UnitOfMeasure.valueOf(subStr[8]), person);
                String strokaYaY = "Элемент успешно добавлен";
                System.out.println(strokaYaY);
                priorityQueue.add(product);
                CommandsForServer.sender(strokaYaY, DC, socket);
            } catch (IllegalArgumentException e) {
                String strokaYaY = "Вы неккоректно ввели данные \nЭлемент не добавлен";
                CommandsForServer.sender(strokaYaY, DC, socket);
            }
        }
    }
    private static void filterGreaterThanPrice(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] arguments = new byte[40];
        boolean flag = false;
        ByteBuffer bufForPrice = ByteBuffer.wrap(arguments);
        bufForPrice.clear();
        socket = DC.receive(bufForPrice);
        bufForPrice.flip();
        char[] array = (new String(arguments)).toCharArray();
        String price = "";
        for (int j = 0; j < 40; j++) {
            if (array[j] != 0) {
                price = price + array[j];
            }
        }
        for (int j = 0; j < 40; j++) {
            arguments[j] = 0;
        }
        String stroka ="";
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() > Integer.parseInt(price)) {
                stroka = stroka + product;
                flag = true;
            }
        }
        if (!flag) {
            stroka = "В коллекци нет таких элментов, чтобы значение price было больше чем " + price;
            CommandsForServer.sender(stroka,DC, socket);
        } else {
            CommandsForServer.sender(stroka,DC, socket);
        }
    }
    private static void countLessThanPrice(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] arguments = new byte[40];
        boolean flag = false;
        ByteBuffer bufForPrice = ByteBuffer.wrap(arguments);
        bufForPrice.clear();
        socket = DC.receive(bufForPrice);
        bufForPrice.flip();
        char[] array = (new String(arguments)).toCharArray();
        String price = "";
        for (int j = 0; j < 40; j++) {
            if (array[j] != 0) {
                price = price + array[j];
            }
        }
        for (int j = 0; j < 40; j++) {
            arguments[j] = 0;
        }
        String stroka ="";
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getPrice() < Integer.parseInt(price)) {
                stroka = stroka + product;
                flag = true;
            }
        }
        if (!flag) {
            stroka = "В коллекци нет таких элментов, чтобы значение price было меньше чем " + price;
            CommandsForServer.sender(stroka,DC, socket);
        } else {
            CommandsForServer.sender(stroka,DC, socket);
        }
    }
    private static void countGreaterThanOwner(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] arguments = new byte[40];
        boolean flag = false;
        ByteBuffer bufForWeight = ByteBuffer.wrap(arguments);
        bufForWeight.clear();
        socket = DC.receive(bufForWeight);
        bufForWeight.flip();
        char[] array = (new String(arguments)).toCharArray();
        String weight = "";
        for (int j = 0; j < 40; j++) {
            if (array[j] != 0) {
                weight = weight + array[j];
            }
        }
        for (int j = 0; j < 40; j++) {
            arguments[j] = 0;
        }
        String stroka ="";
        Object[] products = priorityQueue.toArray();
        for (Object o : products) {
            Product product = (Product) o;
            if (product.getOwner().getWeight() < Integer.parseInt(weight)) {
                stroka = stroka + product;
                flag = true;
            }
        }
        if (!flag) {
            stroka = "В коллекци нет таких элментов, чтобы значение price было больше чем " + weight;
            CommandsForServer.sender(stroka,DC, socket);
        } else {
            CommandsForServer.sender(stroka,DC, socket);
        }
    }
    private static void removeGreater(PriorityQueue<Product> priorityQueue, DatagramChannel DC, SocketAddress socket) throws IOException {
        boolean flag = false;
        byte[] arguments = new byte[40];
        ByteBuffer buf = ByteBuffer.wrap(arguments);
        buf.clear();
        socket = DC.receive(buf);
        buf.flip();
        char[] array = (new String(arguments)).toCharArray();
        String line = "";
        for (int j = 0; j < 40; j++) {
            if (array[j] != 0) {
                line = line + array[j];
            }
        }
        for (int j = 0; j < 40; j++) {
            arguments[j] = 0;
        }
        String delimeter = " ";
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
        if (flag) {
            String stroka = "Элементы успешно удалены";
            CommandsForServer.sender(stroka,DC,socket);
        } else {
            String stroka = "В коллекции нет элементов, которые бы превышали заданный";
            CommandsForServer.sender(stroka,DC,socket);
        }
    }
    static void save(PriorityQueue<Product> priorityQueue, File file, DatagramChannel DC, SocketAddress socket) throws IOException, ParseException {
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

            stroka = String.valueOf(product.getCoordinates().getY()) + " ";
            bufferedOutputStream.write(stroka.getBytes());

            stroka= product.getName()+" ";
            bufferedOutputStream.write(stroka.getBytes());

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
        String message = "Элементы успешно сохранены в коллекцию";
        CommandsForServer.sender(message, DC, socket);
    }
    public static void sender(String stroka, DatagramChannel DC, SocketAddress socket)throws IOException{
        byte[] quantity = String.valueOf(stroka.length()).getBytes();
        ByteBuffer bufForQuantity = ByteBuffer.wrap(quantity);
        bufForQuantity.clear();
        DC.send(bufForQuantity, socket);
        byte[] answer = stroka.getBytes();
        ByteBuffer bufForAnswer = ByteBuffer.wrap(answer);
        bufForAnswer.clear();
        DC.send(bufForAnswer, socket);
    }
    public static void takerCommands(String command, PriorityQueue<Product> productPriorityQueue, File file, DatagramChannel DC, SocketAddress socket) throws Exception {
        Date date = new Date();
        String[] commandAndArgs = command.split(" ");
        switch (commandAndArgs[0]) {
            case "help":
                help(DC, socket);
                break;
            case "info":
                info(productPriorityQueue, DC, socket);
                break;
            case "show":
                show(productPriorityQueue, DC , socket);
                break;
            case "add":
                add(productPriorityQueue,DC, socket);
                break;
            case "clear":
                clear(productPriorityQueue, DC, socket);
                break;
            case "remove_first":
                removeFirst(productPriorityQueue, DC,socket);
                break;
            case "remove_by_id":
                removeById(productPriorityQueue,DC , socket);
                break;
            case "update_id":
                updateId(productPriorityQueue, DC, socket);
                break;
            case "filter_greater_than_price":
                filterGreaterThanPrice(productPriorityQueue, DC, socket);
                break;
            case "count_less_than_price":
                countLessThanPrice(productPriorityQueue, DC, socket);
                break;
            case "count_greater_than_owner":
                countGreaterThanOwner(productPriorityQueue, DC, socket);
                break;
            case "remove_greater":
                removeGreater(productPriorityQueue, DC,socket);
                break;
             case "execute_script":
                ForExecuteScript.executeScript(productPriorityQueue, DC ,socket);
                break;
        }
    }
}