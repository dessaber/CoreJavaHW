package javase01.t06;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m-levin on 06.06.2017.
 * If a pair has key which is already present in the map, old value gets replaced by the new one.
 */
public class ReadProperty {

    private Map<String, String> propertyMap = new TreeMap<>();
    private Iterator<Map.Entry<String, String>> iterator = propertyMap.entrySet().iterator();
    private String inputFile = "src/main/resources/javase01/t06/table.properties";
    private Pattern pattern = Pattern.compile("(\\w+)=(.*)");

    public static void main(String[] args) {
        ReadProperty readProperty = new ReadProperty();
        readProperty.initializePropertyMap(readProperty.inputFile);
        for (Map.Entry entry : readProperty.propertyMap.entrySet())
            System.out.println(entry.getKey() + " " + entry.getValue());

        System.out.println(readProperty.get());
        System.out.println(readProperty.get("absent"));
        System.out.println(readProperty.get("peach"));
    }

    public Map<String, String> getPropertyMap() {
        return propertyMap;
    }

    public void setPropertyMap(Map<String, String> propertyMap) {
        this.propertyMap = propertyMap;
    }

    public String getInputFile() {
        return inputFile;
    }

    public void setInputFile(String inputFile) {
        this.inputFile = inputFile;
    }

    public Iterator<Map.Entry<String, String>> getIterator() {
        return iterator;
    }

    public void setIterator(Iterator<Map.Entry<String, String>> iterator) {
        this.iterator = iterator;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(Pattern pattern) {
        this.pattern = pattern;
    }

    public void initializePropertyMap(String file) {

        if (propertyMap.size() == 0) {
            try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    Matcher m = pattern.matcher(line);
                    if (m.find())
                        propertyMap.put(m.group(1), m.group(2));
                }
            } catch (IOException e) {
                throw new RuntimeException("Can't read the file.");
            }
        } else {
            System.out.println("The map is not empty.");
        }
    }

    public String get(String key) {
        return propertyMap.get(key);
    }

    /**
     * So this method uses TreeMap's order of pairs.
     */
    public String get() {
        if (!iterator.hasNext()) {
            iterator = propertyMap.entrySet().iterator();
        }
        return iterator.next().getValue();
    }
}
