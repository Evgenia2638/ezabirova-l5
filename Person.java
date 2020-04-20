package Lab5;

public class Person {
    private String name;
    private Integer weight;
    private Color eyeColor;
    private Color hairColor;
    private Country nationality;

    public String getName() {
        return name;
    }
    public Integer getWeight() {
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

    public void setName(String name) {
        try {
            if ((name != null) && (!(name.equals("")))) {
                this.name = name;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

        }
    }
    public void setWeight(Integer weight) {
        try {
            if (weight >= 0) {
                this.weight = weight;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

        }
    }
    public void setEyeColor(Color eyeColor) {
        try {
            if (eyeColor != null) {
                this.eyeColor = eyeColor;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

        }
    }
    public void setHairColor(Color hairColor) {
        try {
            if (hairColor != null) {
                this.hairColor = hairColor;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

        }
    }
    public void setNationality(Country nationality) {
        try {
            if (nationality != null) {
                this.nationality = nationality;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {

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
        return "Person ( name: " + getName() +", weight: " + getWeight() +
                ", eye color: " + getEyeColor() + ", hair color: " + getHairColor() + ", nationality: "
                + getNationality();
    }
}
