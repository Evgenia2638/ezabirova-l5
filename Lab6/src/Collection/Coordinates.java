package Collection;

import java.text.MessageFormat;
import java.util.Objects;

public class Coordinates {
    private final float x;
    private final int y;

    public Coordinates(float x, int y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Coordinates guest = (Coordinates) obj;
        return Objects.equals(x, guest.x)&& Objects.equals(y, guest.y);

    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int)x;
        result = prime * result + (int)y;
        return result;
    }

    @Override
    public String toString() {
        String Template = "(x: {0}, y: {1})";
        String StringWithParameters = MessageFormat.format(Template, getX(), getY());
        return StringWithParameters;
    }
}
