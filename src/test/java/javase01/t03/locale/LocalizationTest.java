package javase01.t03.locale;

import org.junit.Test;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 27.05.2017.
 */
public class LocalizationTest {

    @Test
    public void testSingleChangeLocalization() {
        Localization localization = new Localization();
        localization.changeLocale();
        Locale newLocale = localization.getBundle().getLocale();
        assertEquals("en", newLocale.getLanguage());
        assertEquals("US", newLocale.getCountry());
    }

    @Test
    public void testDoubleChangeLocalization() {
        Localization localization = new Localization();
        localization.changeLocale();
        Locale newLocale = localization.getBundle().getLocale();
        assertEquals("en", newLocale.getLanguage());
        assertEquals("US", newLocale.getCountry());
        localization.changeLocale();
        newLocale = localization.getBundle().getLocale();
        assertEquals("ru", newLocale.getLanguage());
        assertEquals("RU", newLocale.getCountry());
    }

    @Test
    public void testInitializeMap() {
        Localization localization = new Localization();
        localization.initializeMap();
        Pattern newPattern = Pattern.compile("answer_\\d");
        Enumeration<String> enumeration = localization.getBundle().getKeys();
        Set<String> expectedSet = new HashSet<>();
        while (enumeration.hasMoreElements()) {
            String currentElement = enumeration.nextElement();
            Matcher newMatcher = newPattern.matcher(currentElement);
            if (newMatcher.matches()) {
                expectedSet.add(currentElement);
            }
        }
        assertEquals(expectedSet, new HashSet<>(localization.getQuestionMap().values()));
    }
}