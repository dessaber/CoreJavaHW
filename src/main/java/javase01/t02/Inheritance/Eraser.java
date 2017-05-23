package javase01.t02.Inheritance;

import java.util.Objects;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Eraser extends Editing {

    public Eraser() {
        this("Just an eraser", Color.BLUE, false, 30);
    }

    public Eraser(String name) {
        this(name, Color.BLUE, false, 30);
    }

    public Eraser(String name, Color color) {
        this(name, color, false, 30);
    }

    public Eraser(String name, double cost) {
        this(name, Color.BLUE, false, cost);
    }

    public Eraser(String name, Color color, boolean inkCompatible) {
        this(name, color, inkCompatible, 30);
    }

    public Eraser(String name, Color color, boolean inkCompatible, double cost) {
        setName(name);
        setColor(color);
        setInkCompatible(inkCompatible);
        setCost(cost);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Eraser eraser = (Eraser) o;
        return isInkCompatible() == eraser.isInkCompatible() &&
                getCost() == eraser.getCost() &&
                getColor() == eraser.getColor() &&
                Objects.equals(getName(), eraser.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getColor(), getName(), isInkCompatible(), getCost());
    }

    @Override
    public String toString() {
        return "Eraser{" +
                "color=" + getColor() +
                ", name='" + getName() + '\'' +
                ", isInkCompatible=" + isInkCompatible() +
                ", cost=" + getCost() +
                '}';
    }

}
