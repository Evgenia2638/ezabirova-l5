package Lab5;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Locale;
import java.util.PriorityQueue;


public class MakerColl{
    /**
     *
     * @param file
     * @return
     * @throws Exception
     */
    static PriorityQueue<Product> CSVtoCollection(File file) throws Exception {
        ComparatorR cam = new ComparatorR();
        InputStreamReader isr = new InputStreamReader(new FileInputStream(file));
        DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy", Locale.ENGLISH);
        PriorityQueue<Product> productPriorityQueue = new PriorityQueue<>(cam);
        int line = isr.read();
        String word = "";
        int i = 0;
        while (line != -1) {
            while (i != 11 && line != -1) {
                Product product = new Product();
                Person person = new Person();
                Coordinates coordinates = new Coordinates();
                while (line != 10 && line != -1) {
                    while (line != 44 && line != -1 && line != 10) {
                        char charecter = (char) line;
                        String s = String.valueOf(charecter);
                        word += s;
                        line = isr.read();
                    }
                    switch (i) {
                        case(0):
                            try {
                                product.setId(Long.valueOf(word));
                            }catch(NumberFormatException ignored){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            }
                            i++;
                            break;
                        case (1):
                            try{
                            product.setName(word);
                            person.setName(word);
                            }catch(NumberFormatException ignored){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            }
                            i++;
                            break;
                        case (2):
                            try {
                                coordinates.setX(Double.valueOf(word));
                                product.setCoordinates(coordinates);
                            } catch (NumberFormatException ex){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            i++;
                            break;
                        case (3):
                            try {
                                coordinates.setY(Long.parseLong(word));
                                product.setCoordinates(coordinates);
                            }catch(NumberFormatException ignored){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            i++;
                            break;
                        case (4):
                            try {
                                product.setCreationDate(formatter.parse(word));
                            }catch(NumberFormatException ignored){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            } catch (ParseException e) {
                                e.printStackTrace();
                            }
                            i++;
                            break;
                        case (5):
                            try {
                                product.setPrice(Float.parseFloat(word));
                            }catch(NumberFormatException ignored){
                                System.err.println("В написании элементов коллекции обнаружена ошибка");
                                System.exit(0);
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                            i++;
                            break;
                        case (6):
                            try{
                                person.setWeight((int) Double.parseDouble(word));
                                product.setOwner(person);
                            }catch(NumberFormatException ignored){
                                 System.err.println("В написании элементов коллекции обнаружена ошибка");
                                 System.exit(0);
                            }
                            i++;
                            break;
                        case (7):
                            if (word.equals("RED\b")||word.equals(("RED"))){
                                try{
                                    person.setEyeColor(Color.RED);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("BLACK\b")||word.equals(("BLACK"))){
                                try{
                                    person.setEyeColor(Color.BLACK);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("BLUE\b")||word.equals(("BLUE"))){
                                try{
                                    person.setEyeColor(Color.BLUE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("YELLOW\b")||word.equals(("YELLOW"))){
                                try{
                                    person.setEyeColor(Color.YELLOW);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("WHITE\b")||word.equals(("WHITE"))){
                                try{
                                    person.setEyeColor(Color.WHITE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("ORANGE\b")||word.equals(("ORANGE"))){
                                try{
                                    person.setEyeColor(Color.ORANGE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            i++;
                            break;
                        case(8):
                            if (word.equals("RED\b")||word.equals(("RED"))){
                                try{
                                    person.setHairColor(Color.RED);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("BLACK\n")||word.equals(("BLACK"))){
                                try{
                                    person.setHairColor(Color.BLACK);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("BLUE\n")||word.equals(("BLUE"))){
                                try{
                                    person.setHairColor(Color.BLUE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("YELLOW\n")||word.equals(("YELLOW"))){
                                try{
                                    person.setHairColor(Color.YELLOW);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("WHITE\n")||word.equals(("WHITE"))){
                                try{
                                    person.setHairColor(Color.WHITE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("ORANGE\n")||word.equals(("ORANGE"))){
                                try{
                                    person.setHairColor(Color.ORANGE);
                                    product.setOwner(person);
                                } catch(Exception e){
                                    e.printStackTrace();
                                }
                            }
                            i++;
                            break;
                        case(9):
                            if (word.equals("RUSSIA\n") || word.equals("RUSSIA")){
                                try{
                                    person.setNationality(Country.RUSSIA);
                                    product.setOwner(person);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("ITALY\n") || word.equals("ITALY")){
                                try{
                                    person.setNationality(Country.ITALY);
                                    product.setOwner(person);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("VATICAN\n") || word.equals("VATICAN")){
                                try{
                                    person.setNationality(Country.VATICAN);
                                    product.setOwner(person);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }

                            if (word.equals("SPAIN\n") || word.equals("SPAIN")){
                                try{
                                    person.setNationality(Country.SPAIN);
                                    product.setOwner(person);
                                } catch (Exception e){
                                    e.printStackTrace();
                                }
                            }
                            i++;
                            break;
                        case (10):
                            if (word.equals("KILOGRAMS\r") || word.equals("KILOGRAMS")) {
                                try {
                                    product.setUnitOfMeasure(UnitOfMeasure.KILOGRAMS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("METERS\r") || word.equals("METERS")) {
                                try {
                                    product.setUnitOfMeasure(UnitOfMeasure.METERS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("CENTIMETERS\r") || word.equals("CENTIMETERS")) {
                                try {
                                    product.setUnitOfMeasure(UnitOfMeasure.CENTIMETERS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }
                            if (word.equals("LITERS\r") || word.equals("LITERS")) {
                                try {
                                    product.setUnitOfMeasure(UnitOfMeasure.LITERS);
                                } catch (Exception e) {
                                    e.printStackTrace();
                                }
                            }i++;
                            break;
                    }
                    word = "";
                    if (line != 10) {
                        line = isr.read();
                    }
                }
                productPriorityQueue.add(product);
            }
            line = isr.read();
            i = 0;
        }

        return productPriorityQueue;
    }
}