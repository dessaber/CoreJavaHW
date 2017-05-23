package javase01.t02.Inheritance;

import java.util.ArrayList;

/**
 * Created by m-levin on 08.05.2017.
 */

public abstract class Stationery {

    public enum Color { BLACK, BLUE, RED, GREEN, VIOLET }

    private String name;
    private Color color;
    private double cost;

    public static void main (String[] args) {

        ArrayList<Stationery> beginnersKit = equipKit();
        for (Stationery s: beginnersKit)
            System.out.println(s);

    }

    public static ArrayList<Stationery> equipKit () {
        ArrayList<Stationery> kit = new ArrayList<>();
        kit.add(new Pen("Erich Krause pen", Color.BLUE, 100, 100));
        kit.add(new Eraser("Tukzar eraser", Color.BLUE, true, 36));
        kit.add(new Marker("Proff marker", Color.GREEN, 80, 70));
        kit.add(new Stapler("Citizen stapler", Color.BLACK, 240));
        kit.add(new Pen("Proff pen", Color.VIOLET, 90, 100));
        kit.add(new Marker("EPAM marker", Color.BLUE, 70, 260));

        return kit;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {

        return color;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public double getCost() {
        return cost;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

}

abstract class WorkWithDocuments extends Stationery {}


abstract class Editing extends Stationery {

    // Some erasers don't work with ink
    private boolean isInkCompatible;

    public boolean isInkCompatible() {
        return isInkCompatible;
    }

    public void setInkCompatible(boolean inkCompatible) {
        isInkCompatible = inkCompatible;
    }

}

abstract class Writing extends Stationery {

}





