package Client;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.SocketAddress;
import java.net.UnknownHostException;
import java.nio.channels.DatagramChannel;
import java.util.Scanner;

public class Client {

        private static final int serverPort = 2528;


        public static void main(String[] args) throws InterruptedException, IOException {
                try {
                        boolean isExit = false;
                        System.out.println("Starting client module.\nConnecting to server ...");
                        SocketAddress socket = new InetSocketAddress("localhost", serverPort);
                        DatagramChannel DC = DatagramChannel.open();
                        DC.connect(socket);
                        System.out.println("Server connected");
                        while (!isExit) {
                                System.out.println("\nВведите комманду \nчтобы увидеть список комманд наберите help");
                                Scanner scanner = new Scanner(System.in);
                                String fromKeyboard = scanner.nextLine();
                                CommandsForClient.takerCommands(fromKeyboard, DC, socket);
                                if (fromKeyboard.equals("exit")) {
                                       // CommandsForClient.recipient(DC,socket);
                                        isExit = true;
                                }
                        }
                }catch (UnknownHostException e){
                    System.err.println("Неизвестный адрес");
                }catch (IOException e){
                    System.err.println("Соединение потеряно");
                    System.err.println("Повторное подключение");
                    System.err.println();
                    main(args);
                }
        }

}