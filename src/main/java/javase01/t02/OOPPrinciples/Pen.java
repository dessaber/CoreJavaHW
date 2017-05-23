package javase01.t02.OOPPrinciples;

import javase01.t02.Inheritance.Stationery;

import java.util.Objects;

/**
 * Created by m-levin on 08.05.2017.
 */
public class Pen {

    private Stationery.Color inkColor;
    private double inkLevel;
    private String name;

    public Pen() {
        this("Just a pen", Stationery.Color.BLACK, 100);
    }

    public Pen(String name) {
        this(name, Stationery.Color.BLACK, 100);
    }


    public Pen(String name, Stationery.Color color) {
        this(name, color, 100);
    }

    public Pen(String name, Stationery.Color color, double inkLevel) {
        this.name = name;
        this.inkColor = inkColor;
        this.inkLevel = inkLevel;
    }

    public String getName() {
        return name;
    }

    public Stationery.Color getInkColor() {
        return inkColor;
    }

    public double getInkLevel() {
        return inkLevel;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setInkColor(Stationery.Color color) {
        this.inkColor = color;
    }

    public void setInkLevel(int inkLevel) {
        this.inkLevel = inkLevel;
    }

    /** Main writing method.
     *
     * @param text what you're actually trying to write
     * @param pressure inkLevel-letters conversion rate.
     */

    public void toWrite(String text, double pressure) {
        if (text != null) {
            double a = pressure * text.toCharArray().length;
            if (a < inkLevel) {
                System.out.println(text);
                inkLevel -= a;
            } else
                System.out.println("No ink - reload!");
        } else
            System.out.println("Nothing to write!");

    }

    public void toWrite(String text) {
        toWrite(text, 0.25);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pen pen = (Pen) o;
        return getInkLevel() == pen.getInkLevel() &&
                getInkColor() == pen.getInkColor() &&
                Objects.equals(getName(), pen.getName());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getInkColor(), getName(), getInkLevel());
    }

    @Override
    public String toString() {
        return "Pen{" +
                "inkColor=" + inkColor +
                ", name='" + name + '\'' +
                ", inkLevel'" +
                '}';
    }
}
