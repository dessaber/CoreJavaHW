package javase01.t04.symbolread;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 31.05.2017.
 */
public class SymbolReadingTest {

    @Test
    public void testReadKeywords() throws Exception {

        SymbolReading reading = new SymbolReading();
        for (Map.Entry entry : initializeExamplesMap().entrySet()) {
            reading.initializeMap();
            reading.readKeywords(entry.getKey().toString());
            assertEquals(entry.getValue(), sumKeywordsCount(reading.getKeywordsMap()));
        }
    }

    public Map<String, Integer> initializeExamplesMap() {
        Map<String, Integer> stringIntegerMap = new HashMap<>();
        stringIntegerMap.put("gdsaf asgdasd gsadg sadg sadg \n f sdaf asf as fsd", 0);
        stringIntegerMap.put("", 0);
        stringIntegerMap.put("Public Sstatic void4", 0);
        stringIntegerMap.put("Integer() [fsdf main string protected", 1);
        stringIntegerMap.put("public static void main", 3);

        return stringIntegerMap;
    }

    public int sumKeywordsCount(Map<String, Integer> map) {
        int result = 0;
        for (Map.Entry e : map.entrySet())
            result += (int) e.getValue();
        return result;
    }
}