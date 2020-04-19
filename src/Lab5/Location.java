package Lab5;

/**
 * класс, отвечающий за расположение объектов коллекции
 */
public class Location{
    private Long x; //Поле не может быть null
    private int y;
    private Long z; //Поле не может быть null
    private String name; //Строка не может быть пустой, Поле может быть null

    public Long getX(){return x;};
    public int getY(){return y;};
    public Long getZ(){return z;};
    public  String getName(){return name;};

    public void setX(Long x){
        try{
            if (x != null){
                this.x=x;
            }else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некорректные двнные");
            System.err.println("Поле не может быть пустым");
        }
    }
    public void setY(int y){
        this.y= y;
    }
    public void setZ(Long z) {
        try {
            if (z != null) {
                this.z = z;
            } else {
                throw new Exception();

            }
        } catch (Exception e) {
            System.err.println("Вы ввели некорректные двнные");
            System.err.println("Поле не может быть пустым");
        }
    }
    public void setName(String name) {
        try {
            if (name!=null && !name.equals("")) {
                this.name = name;
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некорректные двнные");
            System.err.println("Поле не может быть пустым");
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
        Location guest = (Location) obj;
        return y == guest.y && (name.equals(guest.name) || name.equals(guest.getName()))
                && (z.equals(guest.z) || z.equals(guest.getZ()))
                && (x.equals(guest.x) || x.equals(guest.getX()));
    }

    @Override
    public int hashCode() {
        final int prime = 27;
        int result = 1;
            result = prime * result + ((name == null) ? 0 : name.hashCode());
            result = prime * result + ((x == null) ? 0 : x.hashCode());
            result = prime * result + ((z == null) ? 0 : z.hashCode());
            result = prime * result + (int)y;
            return result;
    }

    @Override
    public String toString(){

        return "Name:" + getName()+ " X: " + getX() + " Y: " + getY()+ " Z: "+ getZ();

    }
}
