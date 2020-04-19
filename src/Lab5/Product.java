package Lab5;
import java.util.Date;

/**
 * Класс продукции
 */
public class Product{

    private Long id; //Поле не может быть null, Значение поля должно быть больше 0, Значение этого поля должно быть уникальным, Значение этого поля должно генерироваться автоматически
    private String name; //Поле не может быть null, Строка не может быть пустой
    private Coordinates coordinates; //Поле не может быть null
    private Date creationDate; //Поле не может быть null, Значение этого поля должно генерироваться автоматически
    private float price; //Значение поля должно быть больше 0
    private UnitOfMeasure unitOfMeasure; //Поле не может быть null/**Поле: обладателя продукта**/
    private Person owner; //Поле может быть null

    public Product(long i, String n,Coordinates c,Date cD,float p, UnitOfMeasure uom,Person o){
        this.id=i;
        this.name=n;
        this.coordinates=c;
        this.creationDate=cD;
        this.price=p;
        this.unitOfMeasure=uom;
        this.owner=o;
    }

    /**
     * Конструктор нового метода
     */
    public Product() {
    }
    public Long getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public Date getCreationDate() { return creationDate; }
    public UnitOfMeasure getUnitOfMeasure() { return unitOfMeasure; }
    public float getPrice() { return price; }
    public Person getOwner() { return owner; }

    public void setId(Long id)  {
        //if (id > 0) {
                this.id = id;

            //}
    }
    public void setName(String name) {
        try {
            if ((name != null)&&(!name.equals(""))) {
                this.name = name;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
           // System.err.println("Вы ввели некорректные двнные");
           // System.err.println("Поле не может быть пустым");
        }
    }
    public void setCoordinates(Coordinates coordinates) throws Exception{
        try {
            if (coordinates != null) {
                this.coordinates = coordinates;
            } else {
                throw new Exception();
            }
        }catch (Exception e){

        }
    }
    public void setCreationDate(Date creationDate){
        try{
           if (creationDate != null) {
             this.creationDate = creationDate;
           } else {
               throw new Exception();
           }
        }catch (Exception e) {

        }
    }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws Exception {
        try{
    if(unitOfMeasure != null) {
        this.unitOfMeasure = unitOfMeasure;
    } else {
        throw new Exception();}
    }catch (Exception e){

        }
    }
    public void setPrice(float price) throws Exception{
        try {
            if (price > 0) {
                this.price = price;
            } else {
                throw new Exception();
            }
        }catch(Exception e){

        }
    }
    public void setOwner(Person owner) {
         try{
            if (owner != null) {
               this.owner = owner;
             } else {
                throw new Exception();
            }
      }catch (Exception e){

        }
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
         }
         if (obj == null || obj.getClass() != this.getClass()) {
             return false;
         }
         Product guest = (Product) obj;
     return id.equals(guest.id) && (name.equals(guest.name) || name.equals(guest.getName())) &&
             (coordinates == guest.coordinates || coordinates != null && coordinates.equals(guest.getCoordinates())) &&
             (creationDate == guest.creationDate || creationDate != null && creationDate.equals(guest.getCreationDate())) &&
             (unitOfMeasure == guest.unitOfMeasure || unitOfMeasure != null && unitOfMeasure.equals(guest.getUnitOfMeasure())) &&
             (owner == guest.owner || owner.equals(guest.getOwner()));
    }

    /**
     *
     * @return возвращает хэш-код объекта
     */
    @Override
    public int hashCode() {
        final int prime = 27;
        int result = 1;

        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((coordinates == null) ? 0 : coordinates.hashCode());
        result = prime * result + ((creationDate == null) ? 0 : creationDate.hashCode());
        result = prime * result + ((unitOfMeasure == null) ? 0 : unitOfMeasure.hashCode());
        result = prime * result + (int)price;
        result = prime * result + ((owner == null) ? 0 : owner.hashCode());
        result = prime * result + ((id > 0) ? 0 : Long.hashCode(id));
        return result;

    }

    /**
     *
     * @return выводит поля объекта
     */
    @Override
    public String toString(){
        return "id: "+getId()+", name: "+getName()+ ", price: "+ getPrice()+", coordinates: "+
                getCoordinates()+", owner: "+getOwner()+", unit of measure: "+getUnitOfMeasure()+
                ", creation date:"+getCreationDate();
    }
}
