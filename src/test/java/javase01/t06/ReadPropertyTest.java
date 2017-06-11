package javase01.t06;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 11.06.2017.
 */
public class ReadPropertyTest {

    @Test
    public void testEmptyGet() {
        ReadProperty readProperty = new ReadProperty();
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("Test", "This");
        readProperty.setPropertyMap(stringStringMap);
        assertEquals("This", readProperty.get());
        assertEquals("This", readProperty.get());
    }

    @Test
    public void testEmptyGetWithTwoRecords() {
        ReadProperty readProperty = new ReadProperty();
        Map<String, String> stringStringMap = new HashMap<>();
        stringStringMap.put("Test", "This");
        stringStringMap.put("Now", "That");
        readProperty.setPropertyMap(stringStringMap);
        assertEquals("This", readProperty.get());
        assertEquals("That", readProperty.get());
        assertEquals("This", readProperty.get());
        assertEquals("That", readProperty.get());
    }

    @Test(expected = RuntimeException.class)
    public void testEmptyGetEmptyMap() {
        ReadProperty readProperty = new ReadProperty();
        String value = readProperty.get();
    }
    
    @Test
    public void testGetWrongElement() {
        ReadProperty readProperty = new ReadProperty();
        assertNull(readProperty.get("That"));
    }

    @Test(expected = RuntimeException.class)
    public void testWrongFile() {
        ReadProperty readProperty = new ReadProperty();
        readProperty.initializePropertyMap("Test" + System.nanoTime() + ".txt");
    }
}