package javase01.t02.EnumsAndGenerics;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.TreeSet;

/**
 * Created by m-levin on 12.05.2017.
 */

public class SubjectsApp {

    enum Subject { ALGEBRA, BIOLOGY, INFORMATICS, HISTORY, GEOMETRY, PHYSICS }

    static TreeSet<Group> groupTreeSet = new TreeSet<>(new GroupNameComparator());
    static TreeSet<Student> studentTreeSet = new TreeSet<>(new StudentLastNameComparator().thenComparing(new StudentFirstNameComparator())
                                                                                          .thenComparing(new StudentIdCompartor()));
    private static ArrayList<MainMenuOption> mainMenuOptionArrayList = new ArrayList<>();


    public static void main(String[] args) {

        initializeMainMenu();
        System.out.println("Welcome to the SubjectsApp!");
        while (true)
            try {
                showMainMenu();
                System.out.println("When a number is expected, type any non-digit symbol to close the program.");
                BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                int index = Integer.parseInt(reader.readLine());
                while (index < 0 || index >= mainMenuOptionArrayList.size()) {
                    System.out.println("Wrong index!");
                    index = Integer.parseInt(reader.readLine());
                }
                mainMenuOptionArrayList.get(index).chooseOption();
            } catch (IOException | NumberFormatException e) {
                System.out.println("The program will be closed.");
                return;
            }
    }

    static void initializeMainMenu() {

        mainMenuOptionArrayList.add(new ChoosingAddGroup());
        mainMenuOptionArrayList.add(new ChoosingAddStudent());
        mainMenuOptionArrayList.add(new ChoosingAssignStudent());
        mainMenuOptionArrayList.add(new ChoosingShowGroups());
        mainMenuOptionArrayList.add(new ChoosingShowStudents());
        mainMenuOptionArrayList.add(new ChoosingShowGroupInfo());
        mainMenuOptionArrayList.add(new ChoosingShowStudentInfo());
        mainMenuOptionArrayList.add(new ChoosingRemoveStudentFromGroup());
        mainMenuOptionArrayList.add(new ChoosingRemoveStudent());
        mainMenuOptionArrayList.add(new ChoosingRemoveGroup());
    }

    static void showMainMenu() {

        System.out.println("Available options:");
        System.out.println("0 - add a group");
        System.out.println("1 - add a student");
        System.out.println("2 - assign an existing student to a group");
        System.out.println("3 - show all groups");
        System.out.println("4 - show all students");
        System.out.println("5 - show all students in a group");
        System.out.println("6 - show all groups of a student");
        System.out.println("7 - remove a student from a group");
        System.out.println("8 - remove student");
        System.out.println("9 - remove group");
    }
}
