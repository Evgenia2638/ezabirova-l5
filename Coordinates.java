package Lab5;


import java.util.Objects;

public class Coordinates {
    private Double x;
    private Long y;

    public Coordinates(){
        super();
    }
    Coordinates(Double x,Long y) throws Exception{
        setX(x);
        setY(y);
    }

    public Double getX() {
        return x;
    }
    public Long getY() {
        return y;
    }

    public void setX(Double x) throws Exception {
        try {
            if (x != null) {
                if (!(x < -672.0)) {
                    this.x = x;
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception();
        }
    }
    public void setY(Long y) throws Exception {
        try {
            if (y != null) {
                if (!(y > 151)) {
                    this.y = y;
                } else {
                    throw new Exception();
                }
            } else {
                throw new Exception();
            }
        }catch (Exception e){
            System.err.println("Вы ввели некоретное значение");
            System.err.println("Максимальное значение 151");
            throw new Exception();
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == this){
            return true;
        }
        if(obj == null || obj.getClass() != this.getClass()){
            return false;
        }
        Coordinates guest = (Coordinates) obj;
        return x.equals(guest.x) && Objects.equals(y, guest.y);

    }


    @Override
    public int hashCode(){
        final int prime = 31;
        int result = 1;
        result = prime * result + ((x == null) ? 0 : x.hashCode());
        result = prime * result + ((y == null) ? 0 : y.hashCode());
        return result;
    }

    @Override
    public String toString() {
        return getX() + " " + getY();
    }
}
