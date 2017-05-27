package javase01.t03.locale;

/**
 * Created by m-levin on 24.05.2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import java.io.UnsupportedEncodingException;
import java.util.*;

public class Localization {

    private Map<Integer, String> questionMap;
    private String bundleName = "javase01.t03.locale.TestBundle";
    private ResourceBundle bundle = ResourceBundle.getBundle(bundleName);

    public static void main(String[] args) {

        Localization localization = new Localization();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println(changeEncoding(localization.bundle.getString("hello")));
        System.out.println();
        while (true) {
            System.out.println(changeEncoding(localization.bundle.getString("question_1")));
            System.out.println(changeEncoding(localization.bundle.getString("question_2")));
            System.out.println(changeEncoding(localization.bundle.getString("question_3")));
            System.out.println(changeEncoding(localization.bundle.getString("question_4")));
            System.out.println(changeEncoding(localization.bundle.getString("question_5")));
            System.out.println(changeEncoding(localization.bundle.getString("notification")));

            int i;
            try {
                i = Integer.parseInt(reader.readLine());
                if (i > 0 && i < 6) {
                    System.out.println(changeEncoding(localization.bundle.getString(localization.questionMap.get(i))));
                    System.out.println();
                } else if (i == 6) {
                    localization.changeLocale();
                    System.out.println();
                } else if (i == 7) {
                    break;
                }

            } catch (IOException e) {
                throw new RuntimeException("Input error.");
            }
        }
    }

    public Map<Integer, String> getQuestionMap() {
        return questionMap;
    }

    public ResourceBundle getBundle() {
        return bundle;
    }

    public void setQuestionMap(Map<Integer, String> questionMap) {
        this.questionMap = questionMap;
    }

    public void setBundle(ResourceBundle bundle) {
        this.bundle = bundle;
    }


    public void changeLocale() {
        Locale currentLocale = this.bundle.getLocale();
        if (currentLocale.getCountry().equals("US") && currentLocale.getLanguage().equals("en")) {
            ResourceBundle newBundle = ResourceBundle.getBundle(this.bundleName, new Locale("ru", "RU"));
            setBundle(newBundle);
        } else {
            ResourceBundle newBundle = ResourceBundle.getBundle(this.bundleName, new Locale("en", "US"));
            setBundle(newBundle);
        }
    }

    public static String changeEncoding(String s) {
        String result;
        try {
            result = new String(s.getBytes("ISO-8859-1"),"CP1251");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            result = "";
        }
        return result;
    }

    public void initializeMap() {
        this.questionMap = new HashMap<Integer, String>();
        this.questionMap.put(1, "answer_1");
        this.questionMap.put(2, "answer_2");
        this.questionMap.put(3, "answer_3");
        this.questionMap.put(4, "answer_4");
        this.questionMap.put(5, "answer_5");
    }
}
