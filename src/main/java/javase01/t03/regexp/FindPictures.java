package javase01.t03.regexp;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by m-levin on 25.05.2017.
 */
public class FindPictures {

    private Pattern picturePattern = Pattern.compile("(\\(\\Dис\\.\\s?|рисунк\\D\\s)(\\d+)(,\\s?(\\d+))*(\\sи\\s)*(\\d+)*");
    private Pattern sentencePattern = Pattern.compile("[А-Я][^.?!]*(((\\((Р|р)ис\\.\\s?|рисунк\\D\\s)(\\d+)(\\)|\\sи\\s(\\d+)\\)|,\\s?(\\d+))*).*?)+[.?!]");

    public static void main(String[] args) {

        FindPictures findPictures = new FindPictures();

        try {
            List<String> contents = Files.readAllLines(Paths.get(FindPictures.class.getResource("wiki.html").toURI()), Charset.forName("CP1251"));
            int i = 0;
            for (String line: contents) {
                Matcher matcher = findPictures.sentencePattern.matcher(line);
                while (matcher.find())
                    System.out.println(++i + ". " + matcher.group());
            }
            System.out.println();
            if (findPictures.isPictureConsistent(contents))
                System.out.println("In this article pictures are mentioned consequentially");
            else
                System.out.println("In this article pictures aren't mentioned consequentially");
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException("Error opening resource file");
        }
    }

    public boolean isPictureConsistent(List<String> stringList) {

        int currentPictureNumber = 0;
        int actualPictureNumber = 0;
        for (String string : stringList) {
            Matcher matcher = this.picturePattern.matcher(string);
            while(matcher.find()) {
                for (int i = 2; i <= matcher.groupCount(); i += 2) {
                    String currentGroup = matcher.group(i);
                    if (currentGroup != null) {
                        actualPictureNumber = Integer.parseInt(matcher.group(i));
                        if (currentPictureNumber <= actualPictureNumber) {
                            currentPictureNumber = actualPictureNumber;
                        } else {
                            return false;
                        }
                    } else break;
                }
            }
        }
        return true;
    }

    public Pattern getPicturePattern() {
        return picturePattern;
    }

    public Pattern getSentencePattern() {
        return sentencePattern;
    }

    public void setPicturePattern(Pattern picturePattern) {
        this.picturePattern = picturePattern;
    }

    public void setSentencePattern(Pattern sentencePattern) {
        this.sentencePattern = sentencePattern;
    }
}
