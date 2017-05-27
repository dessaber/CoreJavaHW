package javase01.t03.logging;

import org.junit.Before;
import org.junit.Test;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 27.05.2017.
 */
public class CrazyLoggerTest {

    private CrazyLogger crazyLogger = new CrazyLogger();
    private String inputString = "Hello, dear!";
    private String secondInputString = "Hello, honey!";
    private String  basePattern = "[0-3]?[0-9].[0-3]?[0-9].(?:[0-9]{2})?[0-9]{2}\\s:\\s(?:[01]\\d|2[0-3])-[0-5]\\d\\s-\\s";

    @Test
    public void testAddMessage() {

        CrazyLogger.ChoosingAddMessage choosingAddMessage = crazyLogger.new ChoosingAddMessage();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        choosingAddMessage.executeOption();
        Pattern pattern = Pattern.compile(basePattern + inputString);
        Matcher matcher = pattern.matcher(crazyLogger.getLog());
        assertTrue(matcher.find());
    }

    @Test
    public void testDoubleAddMessage() {

        CrazyLogger.ChoosingAddMessage choosingAddMessage = crazyLogger.new ChoosingAddMessage();
        ByteArrayInputStream in = new ByteArrayInputStream(inputString.getBytes());
        System.setIn(in);
        choosingAddMessage.executeOption();

        ByteArrayInputStream in2 = new ByteArrayInputStream(secondInputString.getBytes());
        System.setIn(in2);
        choosingAddMessage.executeOption();

        Pattern pattern = Pattern.compile(basePattern);
        Matcher matcher = pattern.matcher(crazyLogger.getLog());
        int count = 0;
        while(matcher.find()) {
            count++;
        }
        assertEquals(2, count);
    }
}