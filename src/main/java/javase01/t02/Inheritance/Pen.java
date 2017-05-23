package javase01.t02.Inheritance;

import java.util.Objects;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Pen extends Writing {

    private double inkLevel;

    public Pen() {
        this("Just a pen", Color.BLACK, 15, 50);
    }

    public Pen(String name) {
        this(name, Color.BLACK, 15, 50);
    }

    public Pen(String name, Color color) {
        this(name, color, 15, 50);
    }

    public Pen(String name, double cost) {
        this(name, Color.BLACK, 100, cost);
    }

    public Pen(String name, Color color, double inkLevel) {
        this(name, color, inkLevel, 50);
    }

    public Pen(String name, Color color, double inkLevel, double cost) {
        setName(name);
        setColor(color);
        setInkLevel(inkLevel);
        setCost(cost);
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public void setInkLevel(double getInkLevel) {
        this.inkLevel = getInkLevel;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return getCost() == pen.getCost() &&
                getInkLevel() == getInkLevel() &&
                getColor() == pen.getColor() &&
                Objects.equals(getName(), pen.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getName(), getInkLevel(), getCost());
    }

    @Override
    public String toString() {
        return "Pen{" +
                "color=" + getColor() +
                ", name='" + getName() + '\'' +
                ", inkLevel=" + getInkLevel() +
                ", cost=" + getCost() +
                '}';
    }
}
