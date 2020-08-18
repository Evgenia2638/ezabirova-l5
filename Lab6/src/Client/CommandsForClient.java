package Client;

import java.io.IOException;
import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class CommandsForClient {

    private static void add(DatagramChannel DC, SocketAddress socket) throws IOException {
        System.out.println("Введите данные элемента через пробел"+
                "\nВ таком формате:"+
                "\nX Y Name Weight Eye_Color Hair_Color Country Price Unit_Of_Measure");
        Scanner scanner = new Scanner(System.in);
        try {
            String Keyboard = scanner.nextLine();
            String delimeter = " ";
            if (Keyboard  == null) {
                System.out.println("Вы не ввели аргументы или ввели их не все");
            } else {
                String[] subStr = Keyboard.split(delimeter);
                if ((Float.parseFloat(subStr[0]) <= -148)) {
                    System.err.println("Значение х должно быть больше -148");
                } else {
                    if (Integer.parseInt(subStr[1]) <= -550) {
                        System.err.println("Значение у должно быть больше -550");
                    } else {
                        if (subStr[2] == null) {
                            System.err.println("У элемента должно быть имя");
                        } else {
                            if (Float.parseFloat(subStr[3]) <= 0) {
                                System.err.println("Вес должен быть больше 0");
                            } else {
                                if (Long.parseLong(subStr[7]) <= 0) {
                                    System.err.println("Цена должена быть больше 0");
                                } else {
                                   String command = "add";
                                    CommandsForClient.sender(command,DC,socket);
                                   CommandsForClient.sender(Keyboard,DC,socket);
                                   CommandsForClient.recipient(DC,socket);
                                }
                            }
                        }
                    }
                }
            }

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Элемент не добавлен"+"\nВы не ввели все данные");
        } catch (NumberFormatException ignored) {
            System.out.println( "Вы ввели некорректные данные"+"\nЭлемент не добавлен"+"\nПопробуйте добавить элемент еще раз");
        }
    }
    private static void removeById(DatagramChannel DC, SocketAddress socket) throws IOException {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите id элемента, чтобы удалить его");
        String id = Scanner.nextLine();
        if (id == null) {
            System.err.println("Значение id не может быть пустым");
        } else {
            CommandsForClient.sender(id,DC,socket);
            CommandsForClient.recipient(DC,socket);
        }
    }
    private static void updateID(DatagramChannel DC, SocketAddress socket) throws IOException {
     byte[] flag = new byte[1];
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите id элемента, чтобы обновить его");
        String id = Scanner.nextLine();
        if (id == null) {
            System.err.println("Значение id не может быть пустым");
        } else {
            String command = "update_id";
            CommandsForClient.sender(command,DC,socket);
            CommandsForClient.sender(id, DC, socket);
            ByteBuffer bufForFlag = ByteBuffer.wrap(flag);
            bufForFlag.clear();
            socket = DC.receive(bufForFlag);
            String strFlag = new String(flag);
            if (strFlag.equals("1")) {
                System.out.println("Введите данные элемента через пробел" +
                        "\nВ таком формате:" +
                        "\nX Y Name Weight Eye_Color Hair_Color Country Price Unit_Of_Measure");
                Scanner scanner = new Scanner(System.in);
                    String Keyboard = scanner.nextLine();
                    String delimeter = " ";
                    if (Keyboard == null) {
                        System.out.println("Вы не ввели аргументы или ввели их не все");
                    } else {
                        String[] subStr = Keyboard.split(delimeter);
                        if ((Float.parseFloat(subStr[0]) <= -148)) {
                            System.err.println("Значение х должно быть больше -148");
                        } else {
                            if (Integer.parseInt(subStr[1]) <= -550) {
                                System.err.println("Значение у должно быть больше -550");
                            } else {
                                if (subStr[2] == null) {
                                    System.err.println("У элемента должно быть имя");
                                } else {
                                    if (Float.parseFloat(subStr[3]) <= 0) {
                                        System.err.println("Вес должен быть больше 0");
                                    } else {
                                        if (Long.parseLong(subStr[7]) <= 0) {
                                            System.err.println("Цена должена быть больше 0");
                                        } else {
                                            CommandsForClient.sender(Keyboard, DC, socket);
                                            CommandsForClient.recipient(DC, socket);
                                        }
                                    }
                                }
                            }
                        }
                    }
            }else{
                System.out.println("Нет элемента с таким id");
            }
        }
  }
    private static void filterGreaterThanPrice(DatagramChannel DC, SocketAddress socket) throws IOException {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите значение price");
        String price = Scanner.nextLine();
        if (price == null) {
            System.err.println("Значение price не может быть пустым");
        } else {
            CommandsForClient.sender(price,DC,socket);
            CommandsForClient.recipient(DC,socket);
        }
    }
    private static void countLessThanPrice(DatagramChannel DC, SocketAddress socket) throws IOException {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите значение price");
        String price = Scanner.nextLine();
        if (price == null) {
            System.err.println("Значение price не может быть пустым");
        } else {
            CommandsForClient.sender(price,DC,socket);
            CommandsForClient.recipient(DC,socket);
        }
    }
    private static void countGreaterThanPrice(DatagramChannel DC, SocketAddress socket) throws IOException {
        Scanner Scanner = new Scanner(System.in);
        System.out.println("Введите значение weight");
        String weight = Scanner.nextLine();
        if (weight == null) {
            System.err.println("Значение owner не может быть пустым");
        } else {
            CommandsForClient.sender(weight,DC,socket);
            CommandsForClient.recipient(DC,socket);
        }
    }
    private static void removeGreater(DatagramChannel DC, SocketAddress socket) {
        System.out.println("Введите данные элемента через пробел");
        System.out.println("В таком формате:");
        System.out.println("X Y Weight Price ");
        Scanner scanner = new Scanner(System.in);
        try {
            String line = scanner.nextLine();
            if (line == null) {
                System.out.println("Вы не ввели аргументы");
            } else {
                CommandsForClient.sender(line, DC, socket);
                CommandsForClient.recipient(DC, socket);
            }
        } catch (ArrayIndexOutOfBoundsException e) {
            System.err.println("Вы не ввели все данные");
        } catch (NumberFormatException ignored) {
            System.out.println("Вы ввели некорректные данные");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public  static void executeScript(DatagramChannel DC, SocketAddress socket) throws IOException {
        System.out.println("Введите расположение вашего файла");
        Scanner sc = new Scanner(System.in);
        String wayToFile = sc.nextLine();
        System.out.println(wayToFile);
        CommandsForClient.sender("execute_script",DC,socket);
        CommandsForClient.sender(wayToFile, DC, socket);
        recipient(DC,socket);
    }
    public  static void recipient(DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] quantity = new byte[10];
        ByteBuffer bufForQuantity = ByteBuffer.wrap(quantity);
        bufForQuantity.clear();
        socket = DC.receive(bufForQuantity);
        char[] array = (new String (quantity)).toCharArray();
        StringBuilder strQ = new StringBuilder();
        for (int j =0; j < 10; j++){
            if( (int)array[j] != 0){
                strQ.append(array[j]);
            }
        }
        for (int j = 0; j<10; j++){
            quantity[j]=0;
        }
        bufForQuantity.flip();
        int q = Integer.parseInt(String.valueOf(strQ));
        byte[] answer = new byte[q];
        ByteBuffer bufForAnswer = ByteBuffer.wrap(answer);
        bufForAnswer.clear();
        socket=DC.receive(bufForAnswer);
        String serverAnswer = new String(answer);
        System.out.println(serverAnswer);
    }
    public  static void sender(String stroka, DatagramChannel DC, SocketAddress socket) throws IOException {
        byte[] message = stroka.getBytes();
        ByteBuffer buf = ByteBuffer.wrap(message);
        buf.clear();
        DC.send(buf, socket);
        buf.clear();
    }
    public  static void takerCommands(String command, DatagramChannel DC, SocketAddress socket) throws IOException {
        String[] commandAndArgs = command.split(" ");
        switch (commandAndArgs[0]) {
            case "help":
            case "clear":
            case "show":
            case "info":
            case "remove_first":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.recipient(DC,socket);
                break;
            case "exit":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.recipient(DC,socket);
                System.out.println("Bye");
                System.exit(0);
            case "add":

                CommandsForClient.add(DC,socket);
                break;
            case "remove_by_id":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.removeById(DC, socket);
                break;
            case "update_id":
                CommandsForClient.updateID(DC,socket);
                break;
            case "filter_greater_than_price":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.filterGreaterThanPrice(DC,socket);
                break;
            case "count_less_than_price":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.countLessThanPrice(DC,socket);
                break;
            case "count_greater_than_owner":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.countGreaterThanPrice(DC,socket);
                break;
            case "remove_greater":
                CommandsForClient.sender(command,DC,socket);
                CommandsForClient.removeGreater(DC,socket);
                break;
            case "execute_script":
                CommandsForClient.executeScript(DC,socket);
                break;
            default:
                System.err.println("Некоректная команда");
                System.err.println("Чтобы увидеть список доступных команд наберите help");
        }
    }
}