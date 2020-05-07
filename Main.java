
import java.io.File;
import java.util.*;


public class Main {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Scanner scanner = new Scanner(System.in);
            boolean isExit = false;
            String path = args[0];
            File file = new File(path);
           // File file = new File("D:\\Рабочий стол\\testcsv.csv");
            PriorityQueue<Product> productPriorityQueue = MakerCollection.CSVtoCollection(file);
            while (!isExit) {
                System.out.println("Введите команду");
                System.out.println("Чтобы увидеть список команд наберите help");
                String command = scanner.nextLine();
                if (command.equals("exit")) {
                    isExit = true;
                } else {
                    Commands.takerCommands(command, productPriorityQueue, file);
                }
            }
        }catch (ArrayIndexOutOfBoundsException e){
            System.err.println("Необходимо передать файл с обЪектами");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}