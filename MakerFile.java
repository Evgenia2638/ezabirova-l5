package Lab5;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.Format;
import java.text.SimpleDateFormat;
import java.util.PriorityQueue;

    public class MakerFile{
        /**
         *
         * @param file
         * @param pq
         * @throws FileNotFoundException
         * @throws IOException
         */
        static public void saveFile(File file, PriorityQueue<Product> pq) throws FileNotFoundException, IOException {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            Object[] products = pq.toArray();
            Format format = new SimpleDateFormat("yyyy.MM.dd");
            String separator = ",";
            String enter = "\n";
            String str;
            for (Object o : products) {
                Product product = (Product) o;
                fileOutputStream.write(Math.toIntExact(product.getId()));
                fileOutputStream.write(separator.getBytes());

                fileOutputStream.write(product.getName().getBytes());
                fileOutputStream.write(separator.getBytes());

                str = Integer.toString((int) product.getPrice());
                fileOutputStream.write(str.getBytes());
                fileOutputStream.write(separator.getBytes());

                str = format.format(product.getCreationDate());
                fileOutputStream.write(str.getBytes());
                fileOutputStream.write(separator.getBytes());

            }

        }
    }

