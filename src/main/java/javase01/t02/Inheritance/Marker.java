package javase01.t02.Inheritance;

import java.util.Objects;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Marker extends Writing {

    private double inkLevel;

    public Marker() {
        this("Just a marker", Color.BLACK, 100, 80);
    }

    public Marker(String name) {
        this(name, Color.BLACK, 100, 80);
    }

    public Marker(String name, Color color) {
        this(name, color, 100, 80);
    }

    public Marker (String name, double cost) {
        this(name, Color.BLACK, 100, cost);
    }

    public Marker(String name, Color color, double inkLevel) {
        this(name, color, inkLevel, 80);
    }

    public Marker(String name, Color color, double inkLevel, double cost) {
        setName(name);
        setColor(color);
        setInkLevel(inkLevel);
        setCost(cost);
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public void setInkLevel(double inkLevel) {
        this.inkLevel = inkLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marker marker = (Marker) o;
        return getCost() == marker.getCost() &&
                getInkLevel() == marker.getInkLevel() &&
                getColor() == marker.getColor() &&
                Objects.equals(getName(), marker.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getName(), getInkLevel(), getCost());
    }

    @Override
    public String toString() {
        return "Marker{" +
                "color=" + getColor() +
                ", name='" + getName() + '\'' +
                ", inkLevel=" + getInkLevel() +
                ", cost=" + getCost() +
                '}';
    }

}
