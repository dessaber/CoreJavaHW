package javase01.t04.symbolread;

import java.io.*;
import java.net.URISyntaxException;
import java.util.Map;
import java.util.TreeMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m-levin on 30.05.2017.
 */
public class SymbolReading {

    private static Pattern pattern = Pattern.compile("\\b[a-z]+\\b");
    private Map<String, Integer> keywordsMap;
    private String inputEncoding = "CP1251";
    private String outputEncoding = "CP1251";
    private String inputFile = "Example.txt";
    private File directory = new File("out/t04/symbolread");
    private File outputFile = new File("out/t04/symbolread/Output.txt");

    public static void main(String[] args) {

        SymbolReading symbolReading = new SymbolReading();
        symbolReading.initializeMap();

        try (BufferedReader reader = new BufferedReader((new FileReader(new File(SymbolReading.class.getResource(symbolReading.inputFile).toURI()))))){
            while (reader.ready())
                symbolReading.readKeywords(reader.readLine());
            System.out.println("Input file has been successfully read!");
        } catch (URISyntaxException e) {
            throw new RuntimeException("Wrong resource name.");
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file.");
        }

        if (!symbolReading.directory.exists())
            symbolReading.directory.mkdirs();

        try (Writer writer = new FileWriter(symbolReading.outputFile)) {
            symbolReading.writeKeywords(writer);
            System.out.println("Writing to the output file is done!");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file.");
        }
    }

    public static Pattern getPattern() {
        return pattern;
    }

    public static void setPattern(Pattern pattern) {
        SymbolReading.pattern = pattern;
    }

    public Map<String, Integer> getKeywordsMap() {
        return keywordsMap;
    }

    public void setKeywordsMap(Map<String, Integer> keywordsMap) {
        this.keywordsMap = keywordsMap;
    }

    public String getInputEncoding() {
        return inputEncoding;
    }

    public void setInputEncoding(String inputEncoding) {
        this.inputEncoding = inputEncoding;
    }

    public String getOutputEncoding() {
        return outputEncoding;
    }

    public void setOutputEncoding(String outputEncoding) {
        this.outputEncoding = outputEncoding;
    }

    public void readKeywords(String input) throws UnsupportedEncodingException {
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            String newWord = matcher.group();
            if (keywordsMap.containsKey(newWord))
                keywordsMap.put(newWord, keywordsMap.get(newWord) + 1);
        }
    }

    public void writeKeywords(Writer writer) throws IOException {
        for (Map.Entry entry : keywordsMap.entrySet()) {
            String key = (String) entry.getKey();
            int value = (int) entry.getValue();
            if (value != 0) {
                writer.write(entry.getKey().toString() + " -> " + entry.getValue() + "\r\n");
            }
        }
    }

    public void initializeMap() {
        keywordsMap = new TreeMap<>();
        keywordsMap.put("abstract", 0);
        keywordsMap.put("continue", 0);
        keywordsMap.put("for", 0);
        keywordsMap.put("new", 0);
        keywordsMap.put("switch", 0);
        keywordsMap.put("assert", 0);
        keywordsMap.put("default", 0);
        keywordsMap.put("goto", 0);
        keywordsMap.put("package", 0);
        keywordsMap.put("synchronized", 0);
        keywordsMap.put("boolean", 0);
        keywordsMap.put("do", 0);
        keywordsMap.put("if", 0);
        keywordsMap.put("private", 0);
        keywordsMap.put("this", 0);
        keywordsMap.put("break", 0);
        keywordsMap.put("double", 0);
        keywordsMap.put("implements", 0);
        keywordsMap.put("protected", 0);
        keywordsMap.put("throw", 0);
        keywordsMap.put("byte", 0);
        keywordsMap.put("else", 0);
        keywordsMap.put("import", 0);
        keywordsMap.put("public", 0);
        keywordsMap.put("throws", 0);
        keywordsMap.put("case", 0);
        keywordsMap.put("enum", 0);
        keywordsMap.put("instanceof", 0);
        keywordsMap.put("return", 0);
        keywordsMap.put("transient", 0);
        keywordsMap.put("catch", 0);
        keywordsMap.put("extends", 0);
        keywordsMap.put("int", 0);
        keywordsMap.put("short", 0);
        keywordsMap.put("try", 0);
        keywordsMap.put("char", 0);
        keywordsMap.put("final", 0);
        keywordsMap.put("interface", 0);
        keywordsMap.put("static", 0);
        keywordsMap.put("void", 0);
        keywordsMap.put("class", 0);
        keywordsMap.put("finally", 0);
        keywordsMap.put("long", 0);
        keywordsMap.put("strictfp", 0);
        keywordsMap.put("volatile", 0);
        keywordsMap.put("const", 0);
        keywordsMap.put("float", 0);
        keywordsMap.put("native", 0);
        keywordsMap.put("super", 0);
        keywordsMap.put("while", 0);
    }
}
