import java.text.MessageFormat;

public class Person {
    private  final String name;
    private  final float weight;
    private  final Color eyeColor;
    private  final Color hairColor;
    private  final Country nationality;

    public Person(String name, float weight, Color eyeColor, Color hairColor, Country nationality) {
        this.name = name;
        this.weight = weight;
        this.eyeColor = eyeColor;
        this.hairColor = hairColor;
        this.nationality = nationality;
    }

    public String getName() {
        return name;
    }
    public float getWeight() {
        return weight;
    }
    public Color getEyeColor() {
        return eyeColor;
    }
    public Color getHairColor() {
        return hairColor;
    }
    public Country getNationality() {
        return nationality;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        Person guest = (Person) obj;
        return (name.equals(guest.name) || name.equals(guest.getName()))
                && (weight == guest.weight)
                && (eyeColor == guest.eyeColor || (eyeColor != null && eyeColor.equals(guest.getEyeColor())))
                && (hairColor == guest.hairColor || (hairColor != null && hairColor.equals(guest.getHairColor())))
                && (nationality == guest.nationality || (nationality != null && nationality.equals(guest.getNationality())));
    }

    @Override
    public int hashCode() {
        final int prime = 27;
        int result = 1;
        result = prime * result + ((name == null) ? 0 : name.hashCode());
        result = prime * result + ((eyeColor == null) ? 0 : eyeColor.hashCode());
        result = prime * result + ((hairColor == null) ? 0 : hairColor.hashCode());
        result = prime * result + (int)weight;
        result = prime * result + ((nationality == null) ? 0 : nationality.hashCode());
        return result;
    }

    @Override
    public String toString() {
        String Template = "( name: {0}, weight: {1}, eye color: {2}, hair color: {3}, nationality {4})";
        String StringWithParameters = MessageFormat.format( Template, getName(), getWeight(),getEyeColor(),getHairColor(),getNationality() );
        return StringWithParameters;
    }
}
