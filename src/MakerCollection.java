import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.PriorityQueue;
import java.text.DateFormat;
public class MakerCollection {
    /**
     * @param file
     * @return
     * @throws IOException
     * @throws ParseException
     */
    public static PriorityQueue<Product> CSVtoCollection(File file) throws IOException, ParseException {
        ComparatorR cam = new ComparatorR();
        PriorityQueue<Product> productPriorityQueue = new PriorityQueue<>(cam);
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String delimeter = " ";
        boolean exit = false;
        while (!exit) {
            String line = reader.readLine();
            if (line == null) {
                exit = true;
            } else {
                String[] subStr = line.split(delimeter);
                try {
                    Coordinates coordinates = new Coordinates(Float.parseFloat(subStr[0]), Integer.parseInt(subStr[1]));
                    Person person = new Person(subStr[2], Float.parseFloat(subStr[3]), Color.valueOf(subStr[4]), Color.valueOf(subStr[5]), Country.valueOf(subStr[6]));
                    Product product = new Product((int)Float.parseFloat(subStr[7]), subStr[2], coordinates, formatter.parse(subStr[8]), Long.parseLong(subStr[9]), UnitOfMeasure.valueOf(subStr[10]), person);
                    productPriorityQueue.add(product);
                } catch (IllegalArgumentException x) {
                    System.err.println("Ошибка в чтении файла");
                    System.exit(0);
                }
            }
        }
            return productPriorityQueue;
        }
    }
