package javase01.t02.Inheritance;

import java.util.Objects;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Stapler extends WorkWithDocuments {

    public Stapler() {
        this("Just a stapler", Color.VIOLET, 200);
    }

    public Stapler(String name) {
        this(name, Color.VIOLET, 200);
    }

    public Stapler(String name, Color color) {
        this(name, color, 200);
    }

    public Stapler (String name, double cost) {
        this(name, Color.VIOLET, cost);
    }

    public Stapler(String name, Color color, double cost) {
        setName(name);
        setColor(color);
        setCost(cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Stapler stapler = (Stapler) o;
        return getCost() == stapler.getCost() &&
                Objects.equals(getColor(), stapler.getColor()) &&
                Objects.equals(getName(), stapler.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getName(), getCost());
    }

    @Override
    public String toString() {
        return "Stapler{" +
                "color=" + getColor() +
                ", name='" + getName() + '\'' +
                ", cost=" + getCost() +
                '}';
    }

}
