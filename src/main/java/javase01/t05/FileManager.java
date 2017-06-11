package javase01.t05;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by m-levin on 07.06.2017.
 */
public class FileManager {

    private File path = new File(".");
    private Map<Integer, MenuOption> menuOptionMap;
    private boolean isGoing = true;

    public static void main(String[] args) throws IOException {
        FileManager fileManager = new FileManager();
        fileManager.initializeOptionMap();
        System.out.println("Welcome to the FileManagerApp!");
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {
            while (fileManager.isGoing) {
                for (Map.Entry entry : fileManager.menuOptionMap.entrySet())
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                fileManager.menuOptionMap.get(Integer.parseInt(reader.readLine())).executeOption();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public File getPath() {
        return path;
    }

    public void setPath(File path) {
        this.path = path;
    }

    public Map<Integer, MenuOption> getMenuOptionMap() {
        return menuOptionMap;
    }

    public void setMenuOptionMap(Map<Integer, MenuOption> menuOptionMap) {
        this.menuOptionMap = menuOptionMap;
    }

    public boolean isGoing() {
        return isGoing;
    }

    public void setGoing(boolean going) {
        isGoing = going;
    }

    interface MenuOption {
        void executeOption() throws IOException;
    }

    class FileCreation implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Input file name");
            if (makeFile(new File(reader.readLine())))
                System.out.println("Successful!");
            else
                System.out.println("Error creating the file.");
        }

        boolean makeFile(File file) throws IOException {
            return file.createNewFile();
        }

        @Override
        public String toString() {
            return "Create a file";
        }
    }

    class FileContents implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            System.out.println("Which file do you want to see contents of?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            for (Object string : getFileContents(reader.readLine()))
                System.out.println(string);
        }

        public Object[] getFileContents(String fileName) throws IOException {
            File file = new File(fileName);
            Object[] fileContents = new String[0];
            if (file.exists() && file.isFile() && file.canRead()) {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                fileContents = reader.lines().toArray();
                reader.close();
            }
            return fileContents;
        }

        @Override
        public String toString() {
            return "Show contents of a file.";
        }
    }

    class FileWriting implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            System.out.println("Which file would you like to write to?");
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            String fileName = reader.readLine();
            System.out.println("What message would you like to add?");
            String text = reader.readLine();
            if (writeFile(fileName, text))
                System.out.println("Successful!");
            else
                System.out.println("Error writing to the file.");
        }

        public boolean writeFile(String fileName, String text) throws IOException {
            File file = new File(fileName);
            boolean result = false;
            if (!file.isAbsolute()) {
                file = new File(path.getCanonicalPath() + File.separator + fileName);
            }
            if (file.exists() && file.isFile() && file.canWrite()) {
                BufferedWriter writer = new BufferedWriter(new FileWriter(file, true));
                writer.write(text);
                writer.close();
                result = true;
            }
            return result;
        }

        @Override
        public String toString() {
            return "Write to a file";
        }
    }

    class DirectoryContents implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            for (String s : getDirectoryContents())
                System.out.println(s);
        }

        public String[] getDirectoryContents() {
            String[] contents = new String[0];
            if (path.canRead())
                contents = path.list();
            return contents;
        }

        @Override
        public String toString() {
            return "Show directory contents";
        }
    }

    class DirectoryEntrance implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("Which directory do you want to enter?");
            if (enterDirectory(reader.readLine()))
                System.out.println("Successful!");
            else
                System.out.println("Can't enter specified directory.");
        }

        public boolean enterDirectory(String text) throws IOException {
            boolean result = false;
            File newPath = new File(text);
            if (!newPath.isAbsolute()) {
                newPath = new File(path.getCanonicalPath() + File.separator + text);
            }
            if (newPath.exists() && newPath.isDirectory() && newPath.canExecute()) {
                setPath(newPath);
                result = true;
            }
            return result;
        }

        @Override
        public String toString() {
            return "Enter a directory";
        }
    }

    class AppClosing implements MenuOption {

        @Override
        public void executeOption() throws IOException {
            isGoing = false;
        }

        @Override
        public String toString() {
            return "Close the app";
        }
    }

    public void initializeOptionMap() {
        Map<Integer, MenuOption> newMenuOptionMap = new HashMap<>();
        newMenuOptionMap.put(0, new DirectoryContents());
        newMenuOptionMap.put(1, new FileCreation());
        newMenuOptionMap.put(2, new FileWriting());
        newMenuOptionMap.put(3, new DirectoryEntrance());
        newMenuOptionMap.put(4, new FileContents());
        newMenuOptionMap.put(5, new AppClosing());
        menuOptionMap = newMenuOptionMap;
    }
}
