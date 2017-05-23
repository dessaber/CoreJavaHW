package javase01.t02.Interfaces;

import javase01.t02.Inheritance.Stationery;

import java.util.ArrayList;
import java.util.Comparator;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Comparisons {

   public static void main(String[] args) {

       ArrayList<Stationery> kit = Stationery.equipKit();
       StationeryCostComparator costComparator = new StationeryCostComparator();
       StationeryNameComparator nameComparator = new StationeryNameComparator();

       kit.sort(nameComparator);
       for (Stationery s: kit)
           System.out.println(s);
       System.out.println();

       kit.sort(costComparator);
       for (Stationery s: kit)
           System.out.println(s);
       System.out.println();

       kit.sort(costComparator.thenComparing(nameComparator));
       for (Stationery s: kit)
           System.out.println(s);
       System.out.println();

   }

}

class StationeryCostComparator implements Comparator<Stationery> {

    @Override
    public int compare(Stationery s1, Stationery s2) {
        return s1.getCost() == s2.getCost() ? 0 : s1.getCost() > s2.getCost() ? 1 : -1;
    }
}

class StationeryNameComparator implements Comparator<Stationery> {

    @Override
    public int compare(Stationery s1, Stationery s2) {
        return  s1.getName().compareToIgnoreCase(s2.getName());
    }
}

