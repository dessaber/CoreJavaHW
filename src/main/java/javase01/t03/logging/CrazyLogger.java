package javase01.t03.logging;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by m-levin on 24.05.2017.
 */

public class CrazyLogger {

    private StringBuilder log = new StringBuilder();
    private Map<Integer, LoggerOption> integerLoggerOptionMap;
    private String delimeter = "###DEL###";

    interface LoggerOption {
        void executeOption();
    }

    class ChoosingAddMessage implements LoggerOption {

        @Override
        public void executeOption() {
            System.out.println("Input your message:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            log.append(new SimpleDateFormat("dd-MM-YYYY : HH-mm").format(new Date()));
            log.append(" - ");
            try {
                log.append(reader.readLine());
            } catch (IOException e){
                throw new RuntimeException("Incorrect input");
            }
            log.append(delimeter);
        }
    }

    class ChoosingShowAllMessages implements LoggerOption {

        @Override
        public void executeOption() {
            for (String s: log.toString().split(delimeter))
                System.out.println(s);
            System.out.println();
        }
    }

    class ChoosingSearch implements LoggerOption {

        @Override
        public void executeOption() {
            System.out.println("Input search phrase:");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            try {
                String phrase = reader.readLine();
                if ("".equals(phrase))
                    System.out.println();
                else if (log.toString().length() == 0)
                    System.out.println("");
                else
                    for (String s: log.toString().split(delimeter))
                        if (s.contains(phrase))
                            System.out.println(s);
                System.out.println();
            } catch (IOException e){
                throw new RuntimeException("Incorrect input");
            }
        }
    }

    public static void main(String[] args) {

        CrazyLogger crazyLogger = new CrazyLogger();
        Map<Integer, LoggerOption> newMap = new HashMap<>();

        System.out.println("Welcome to the CrazyLogger!");

        newMap.put(1, crazyLogger.new ChoosingAddMessage());
        newMap.put(2, crazyLogger.new ChoosingSearch());
        newMap.put(3, crazyLogger.new ChoosingShowAllMessages());
        crazyLogger.setIntegerLoggerOptionMap(newMap);

        Scanner scanner = new Scanner(System.in);
        int i;
        while (true) {
            crazyLogger.showMenu();
            i = scanner.nextInt();
            if (i > 0 && i < 4)
                crazyLogger.integerLoggerOptionMap.get(i).executeOption();
            else if (i == 4)
                break;
        }
    }

    public String getDelimeter() {
        return delimeter;
    }

    public void setDelimeter(String delimeter) {
        this.delimeter = delimeter;
    }

    public StringBuilder getLog() {
        return log;
    }

    public void setLog(StringBuilder log) {
        this.log = log;
    }

    public Map<Integer, LoggerOption> getIntegerLoggerOptionMap() {
        return integerLoggerOptionMap;
    }

    public void setIntegerLoggerOptionMap(Map<Integer, LoggerOption> integerLoggerOptionMap) {
        this.integerLoggerOptionMap = integerLoggerOptionMap;
    }

    public void showMenu() {
        System.out.println("Here's the list of all options:");
        System.out.println("1 - add new message to the log;");
        System.out.println("2 - search a phrase in the log;");
        System.out.println("3 - show all messages;");
        System.out.println("4 - close the program.");
    }
}
