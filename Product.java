import java.text.MessageFormat;
import java.util.Date;

public class Product {
    private  final int id;
    private  final String name;
    private  final Coordinates coordinates;
    private  final Date creationDate;
    private  final long price;
    private  final UnitOfMeasure unitOfMeasure;
    private  final Person owner;

   public Product(int id, String name, Coordinates coordinates, Date creationDate, long price, UnitOfMeasure unitOfMeasure, Person owner) {
        super();
        this.id = id;
        this.name = name;
        this.coordinates = coordinates;
        this.creationDate = creationDate;
        this.price = price;
        this.unitOfMeasure = unitOfMeasure;
        this.owner = owner;
    }

    public int getId() { return id; }
    public String getName() { return name; }
    public Coordinates getCoordinates() { return coordinates; }
    public Date getCreationDate() { return creationDate; }
    public UnitOfMeasure getUnitOfMeasure() { return unitOfMeasure; }
    public float getPrice() { return price; }
    public Person getOwner() { return owner; }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Product guest = (Product) obj;
        return  (name.equals(guest.name) || name.equals(guest.getName())) &&
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
        String Template = "id: {0}, name: {1}, price: {2}, coordinates: {3}, owner: {4}, unit of measure: {5}, creation date: {6} ;";
        String StringWithParameters  = MessageFormat.format( Template,  getId(), getName(),getPrice(),getCoordinates(),getOwner(),getUnitOfMeasure(), getCreationDate());
        return StringWithParameters  ;
    }
}