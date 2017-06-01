package javase01.t02.Classes;

import javase01.t02.Inheritance.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by m-levin on 09.05.2017.
 */
public class Counting {

    public ArrayList<Stationery> kit = new ArrayList<>();
    public HashMap<Integer, AddingStationery> stationeryHashMap = new HashMap<>();
    public HashMap<Integer, ChoosingOption> choosingOptionHashMap = new HashMap<>();

    public static void main(String[] args) {

        Counting counting = new Counting();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        counting.choosingOptionHashMap.put(1, counting.new ChoosingAdding());
        counting.choosingOptionHashMap.put(2, counting.new ChoosingRemoving());
        counting.choosingOptionHashMap.put(3, counting.new ChoosingShowing());
        counting.choosingOptionHashMap.put(4, counting.new ChoosingSumming());

        counting.stationeryHashMap.put(1, counting.new AddingPen());
        counting.stationeryHashMap.put(2, counting.new AddingMarker());
        counting.stationeryHashMap.put(3, counting.new AddingStapler());
        counting.stationeryHashMap.put(4, counting.new AddingEraser());

        try {
            System.out.println("Welcome to the KitAccount!");
            System.out.println("When a number is expected, type any non-digit symbol to close the program.");
            while (true) {
                showMainMenu();
                int option = Integer.parseInt(reader.readLine());
                if (counting.choosingOptionHashMap.containsKey(option))
                    counting.choosingOptionHashMap.get(option).chooseOption();
                else
                    System.out.println("No such option!");
            }
        } catch (NumberFormatException e) {
            System.out.println("The program will be closed.");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    interface AddingStationery {

        public void addItem() throws IOException, NumberFormatException;

    }

    class AddingPen implements AddingStationery {

        @Override
        public void addItem() throws IOException, NumberFormatException {
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                System.out.println("Input pen's name:");
                String name = reader.readLine();
                System.out.println("Now input its cost:");
                double cost = Double.parseDouble(reader.readLine());
                kit.add(new Pen(name, cost));
        }
    }

    class AddingMarker implements AddingStationery {

        @Override
        public void addItem() throws IOException, NumberFormatException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input marker's name:");
            String name = reader.readLine();
            System.out.println("Now input its cost:");
            double cost = Double.parseDouble(reader.readLine());
            kit.add(new Marker(name, cost));
        }
    }

    class AddingEraser implements AddingStationery {

        @Override
        public void addItem() throws IOException, NumberFormatException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input eraser's name:");
            String name = reader.readLine();
            System.out.println("Now input its cost:");
            double cost = Double.parseDouble(reader.readLine());
            kit.add(new Eraser(name, cost));
        }
    }

    class AddingStapler implements AddingStationery {

        @Override
        public void addItem() throws IOException, NumberFormatException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input stapler's name:");
            String name = reader.readLine();
            System.out.println("Now input its cost:");
            double cost = Double.parseDouble(reader.readLine());
            kit.add(new Stapler(name, cost));
        }
    }

    interface ChoosingOption {

        public void chooseOption() throws IOException, NumberFormatException;

    }

    class ChoosingAdding implements ChoosingOption {

        @Override
        public void chooseOption() throws IOException, NumberFormatException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            showToolsMenu();
            int option = Integer.parseInt(reader.readLine());
            if (stationeryHashMap.containsKey(option)) {
                stationeryHashMap.get(option).addItem();
            } else
                System.out.println("No such option!");
        }

    }

    class ChoosingShowing implements ChoosingOption {

        @Override
        public void chooseOption() throws IOException, NumberFormatException {
            for (Stationery s: kit)
                System.out.println(s);
        }

    }

    class ChoosingRemoving implements ChoosingOption {

        @Override
        public void chooseOption() throws IOException, RuntimeException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input index:");
            int index = Integer.parseInt(reader.readLine());

            if (index < kit.size() && index > 0)
                kit.remove(index);
            else
                System.out.println("No such index!");
        }
    }

    class ChoosingSumming implements ChoosingOption {

        @Override
        public void chooseOption() throws IOException, RuntimeException {
            double sum = 0;
            for (Stationery s: kit) {
                sum += s.getCost();
            }
            System.out.println("Total sum of all stationery items is " + sum + ".");
        }
    }

    public static void showMainMenu() {
        System.out.println("Available options:");
        System.out.println("1 - add an item;");
        System.out.println("2 - remove an item;");
        System.out.println("3 - show all items;");
        System.out.println("4 - count total cost.");
    }

    public static void showToolsMenu() {
        System.out.println("Available options:");
        System.out.println("1 - add a pen;");
        System.out.println("2 - add a marker;");
        System.out.println("3 - add a stapler;");
        System.out.println("4 - add an eraser.");
    }

}

