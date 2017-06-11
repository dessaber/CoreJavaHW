package javase01.t05;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.xml.crypto.dsig.spec.ExcC14NParameterSpec;
import java.io.*;
import java.util.Scanner;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 08.06.2017.
 */
public class FileManagerTest {

    File file;
    FileManager fileManager;

    @Before
    public void initializeFileManager() {
        fileManager = new FileManager();
    }

    @Test
    public void makeFile() throws Exception {
        FileManager.FileCreation creation = fileManager.new FileCreation();
        file = new File("effort" + System.nanoTime() + ".txt");
        assertEquals(true, creation.makeFile(file));
        assertEquals(false, creation.makeFile(file));
    }

    @Test(expected = IOException.class)
    public void invalidMakeFile() throws Exception {
        FileManager.FileCreation creation = fileManager.new FileCreation();
        file = new File("\\n.txt");
        creation.makeFile(file);
    }

    @Test
    public void getEmptyContents() throws Exception {
        FileManager.FileContents fileContents = fileManager.new FileContents();
        FileManager.FileCreation fileCreation = fileManager.new FileCreation();
        file = new File("effort" + System.nanoTime() + ".txt");
        fileCreation.makeFile(file);
        assertArrayEquals(new Object[0], fileContents.getFileContents(file.getName()));
    }

    @Test
    public void getSomeContents() throws Exception {
        FileManager.FileContents fileContents = fileManager.new FileContents();
        String[] stringArray = new String[2];
        stringArray[0] = "TestContents";
        stringArray[1] = "OneMoreTime";
        file = new File("effort" + System.nanoTime() + ".txt");
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        for (String string : stringArray)
            writer.write(string + "\n");
        writer.close();
        Object[] contents = fileContents.getFileContents(file.getName());
        int i = 0;
        for (String string : stringArray) {
            assertEquals(string, (String) contents[i]);
            i++;
        }
    }

    @After
    public void deleteFile() {
        if (file.exists()) {
            file.setWritable(true);
            assertEquals(true, file.delete());
        }
    }
}