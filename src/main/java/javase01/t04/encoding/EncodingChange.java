package javase01.t04.encoding;

import java.io.*;

/**
 * Created by m-levin on 31.05.2017.
 */
public class EncodingChange {

    private File directory = new File("out/t04/encoding");
    private File file = new File("out/t04/encoding/Output.txt");

    public static void main(String[] args) {

        EncodingChange encodingChange = new EncodingChange();
        byte[] input;
        try (BufferedInputStream inputStream = new BufferedInputStream(EncodingChange.class.getResourceAsStream("Input.txt"))) {
            input = new byte[inputStream.available()];
            for (int i = 0; i < input.length; i++) {
               input[i] = (byte) inputStream.read();
            }
            System.out.println("Input file is successfully read!");
        } catch (IOException e) {
            throw new RuntimeException("Error reading the file.");
        }

        if (!encodingChange.directory.exists())
            encodingChange.directory.mkdirs();
        try (BufferedOutputStream outputStream = new BufferedOutputStream(new FileOutputStream(encodingChange.file))) {
            outputStream.write(new String(input, "UTF-8").getBytes("UTF-16"));
            System.out.println("Writing to the output file is done!");
        } catch (IOException e) {
            throw new RuntimeException("Error writing to the file.");
        }
    }
}
