package javase01.t01.notepad;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by m-levin on 24.04.2017.
 */
public class Notepad {

    private NotepadRecord[] contents = new NotepadRecord[2];
    private int rowsCount = 0;

    /**
     * Adds a new record after the last record using NotepadRecord parameterized constructor
     * @param text What your record actually says
     */
    public void addRecord(String text) {
        if (rowsCount == contents.length) {
            NotepadRecord[] newContents = new NotepadRecord[2*(contents.length)];
            System.arraycopy(contents, 0, newContents, 0, contents.length);
            contents = newContents;
        }
        contents[rowsCount] = new NotepadRecord(text);
        rowsCount++;
    }

    /**
     * Removes a record from specified row, inserts the rest of the array in this position
     * @param index Row with what number you want to erase
     */
    public void removeRecord(int index) {

        if (index > contents.length - 1 && index < 0)
            System.out.println("Wrong row number to update");
        else {
            System.arraycopy(contents, index + 1, contents, index, contents.length - index - 1);
        }
        contents[contents.length - 1] = null;
        rowsCount--;
    }

    /**
     * Change record
     * @param index Record with what number will be changed
     * @param text New record's text
     */
    public void updateRecord(int index, String text) {
        if (index > contents.length - 1 && index < 0)
            System.out.println("Wrong row number to update");
        else
            contents[index].setText(text);
    }

    /**
     * Simple output.
     */
    public void showRecords() {
        System.out.println();
        for (int i = 0; i < rowsCount; ++i)
            if (contents[i].getText().equals(""))
                System.out.println("");
            else
                System.out.println(contents[i].getText() + " " + contents[i].getTimePoint());
        System.out.println();
    }

    /**
     * This methods shows all available options.
     */
    public void showMenu() {
        System.out.println("Available options (type to choose):");
        System.out.println("1 - add a record");
        System.out.println("2 - remove a record");
        System.out.println("3 - update a record");
        System.out.println("4 - show all records");
        System.out.println("Type any non-digit symbol to close the program.");
    }

    /**
     * Entry point represents console dialog.
     * @param args
     */
    public static void main(String[] args) {

        System.out.println("Welcome to The Notepad!");

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Notepad notepad = new Notepad();
        try {
            int b;
            while (true) {
                notepad.showMenu();
                b = Integer.parseInt(reader.readLine());
                /* Could've used Strategy pattern here, but the task says: "Build TWO classes..." */
                switch (b) {
                    case 1:
                        System.out.println("Type in your text: ");
                        notepad.addRecord(reader.readLine());
                        break;
                    case 2:
                        System.out.println("Type in index of the row:");
                        notepad.removeRecord(Integer.parseInt(reader.readLine()));
                        break;
                    case 3:
                        int c;
                        System.out.println("Type in index of the row:");
                        c = Integer.parseInt(reader.readLine());
                        System.out.println("Type in your text: ");
                        notepad.updateRecord(c, reader.readLine());
                        break;
                    case 4:
                        notepad.showRecords();
                        break;
                    default:
                        System.out.println("Wrong option.");
                }
            }
        } catch (IOException | NumberFormatException e) {
            System.out.println("The program will be closed.");
        }
    }
}

