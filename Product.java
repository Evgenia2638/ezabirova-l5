package Lab5;
import java.util.Date;


public class Product{

    private Long id;
    private String name;
    private Coordinates coordinates;
    private Date creationDate;
    private float price;
    private UnitOfMeasure unitOfMeasure;
    private Person owner;

    public Product(long i, String n,Coordinates c,Date cD,float p, UnitOfMeasure uom,Person o){
        this.id=i;
        this.name=n;
        this.coordinates=c;
        this.creationDate=cD;
        this.price=p;
        this.unitOfMeasure=uom;
        this.owner=o;
    }

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
                this.id = id;
    }
    public void setName(String name) {
        try {
            if ((name != null)&&(!name.equals(""))) {
                this.name = name;
            } else {
                throw new Exception();
            }
        }catch (Exception ignored){
        }
    }
    public void setCoordinates(Coordinates coordinates) throws Exception{
        try {
            if (coordinates != null) {
                this.coordinates = coordinates;
            } else {
                throw new Exception();
            }
        }catch (Exception ignored){

        }
    }
    public void setCreationDate(Date creationDate){
        try{
           if (creationDate != null) {
             this.creationDate = creationDate;
           } else {
               throw new Exception();
           }
        }catch (Exception ignored) {

        }
    }
    public void setUnitOfMeasure(UnitOfMeasure unitOfMeasure) throws Exception {
        try{
    if(unitOfMeasure != null) {
        this.unitOfMeasure = unitOfMeasure;
    } else {
        throw new Exception();}
    }catch (Exception ignored){

        }
    }
    public void setPrice(float price) throws Exception{
        try {
            if (price > 0) {
                this.price = price;
            } else {
                throw new Exception();
            }
        }catch(Exception ignored){

        }
    }
    public void setOwner(Person owner) {
         try{
            if (owner != null) {
               this.owner = owner;
             } else {
                throw new Exception();
            }
      }catch (Exception ignored){

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


    @Override
    public String toString(){
        return "id: "+getId()+", name: "+getName()+ ", price: "+ getPrice()+", coordinates: "+
                getCoordinates()+", owner: "+getOwner()+", unit of measure: "+getUnitOfMeasure()+
                ", creation date:"+getCreationDate();
    }
}
