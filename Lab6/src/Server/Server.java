package Server;

import Collection.MakerCollection;
import Collection.Product;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.*;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;
import java.text.ParseException;
import java.util.PriorityQueue;


public class Server {
    private static final int serverPort = 2528;

    public static void main(String[] args) throws InterruptedException, IOException, ParseException {
        byte[] command = new byte[40];
        System.out.println("ServerSocket awaiting connections...");
        SocketAddress socket = new InetSocketAddress(serverPort);
        DatagramChannel DC = DatagramChannel.open();
        System.out.println("Connection from " + socket + "!");
        DC.bind(socket);
        try {
            File file = new File("D:\\Рабочий стол\\testcsv.csv");
           // String path = args[0];
           // File file = new File(path);
            PriorityQueue<Product> productPriorityQueue = MakerCollection.CSVtoCollection(file);
            boolean isExit = false;
            while (!isExit) {
                ByteBuffer buf = ByteBuffer.wrap(command);
                buf.clear();
                socket = DC.receive(buf);
                char[] array = (new String(command)).toCharArray();
                StringBuilder usersCommand = new StringBuilder();
                for (int j = 0; j < 40; j++) {
                    if ((int) array[j] != 0) {
                        usersCommand.append(array[j]);
                    }
                }
                if (usersCommand.toString().equals("exit")) {
                    isExit = true;
                    System.err.println("Bye");
                    CommandsForServer.save(productPriorityQueue,file,DC,socket);
                    System.exit(0);
                } else {
                    buf.clear();
                    CommandsForServer.takerCommands(usersCommand.toString(), productPriorityQueue, file, DC, socket);
                    for (int j = 0; j < 40; j++) {
                       command[j] = 0;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            String errorFileNotFound = "File not found";
            byte[] error = errorFileNotFound.getBytes();
            ByteBuffer bufForError = ByteBuffer.wrap(error);
            DC.send(bufForError, socket);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
