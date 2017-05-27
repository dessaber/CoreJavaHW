package javase01.t03.regexp;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

/**
 * Created by m-levin on 27.05.2017.
 */
public class FindPicturesTest {

    @Test
    public void testPicturePattern() {

        FindPictures findPictures = new FindPictures();
        for (Map.Entry entry : initializeExampleMap().entrySet()) {
            assertEquals(entry.getValue(), findPictures.getPicturePattern().matcher((String)entry.getKey()).find());
        }
    }

    @Test
    public void testSentencePattern() {

        FindPictures findPictures = new FindPictures();
        for (Map.Entry entry : initializeExampleMap().entrySet()) {
            assertEquals(entry.getValue(), findPictures.getSentencePattern().matcher((String)entry.getKey()).matches());
        }
    }

    public Map<String, Boolean> initializeExampleMap() {

        Map<String, Boolean> stringBooleanMap = new HashMap<>();
        stringBooleanMap.put("В нижней части рисунка 17 показан магнетон.", true);
        stringBooleanMap.put("Спиновые (сторонние) неэлектростатические заряды протонов в трёх альфах ядра углерода (Рис. 8) называются свободными неэлектростатическими зарядами.", true);
        stringBooleanMap.put("Электроны в атомах неподвижны, максимальное количество протонов в ядрах и электронов в атомах &nbsp;не может превышать число 8.", false);
        stringBooleanMap.put("Полная принципиаальная структура коллапсара-электрона (позитрона) показана на рисунке 3.", true);
        stringBooleanMap.put("Почему чешуйку графита (Рис. 18 и 21) необходимо называть плоской раскрытой молекулой графита С<sub>54</sub>?", true);
        stringBooleanMap.put("Плоские, раскрытые молекулы графита С<sub>54</sub> (Рис. 21) в особых внешних условиях «сворачиваются» в закрытые нейтральные молекулы графита С<sub>60</sub> – фуллерены (Рис. 22).", true);
        stringBooleanMap.put("Новое описание истинной структуры ядра атома углерода основано на универсальных свойствах коллапсаров-нуклонов в силовых структурах трёх альф, расположенных в ядерной трубке атома углерода с взаимным относительным смещением на 60 (рис. 8).", true);
        stringBooleanMap.put("Потоки гамма-коллапсаров (Картинка 2) индуцируют жёсткое электромагнитное излучение", false);
        stringBooleanMap.put("По какой причине спиновые неэлектростатические заряды лёгких нейтронов (Рис. 15,16) гелиевой альфы атома углерода в ядре молекулы метана, нейтрализованы в собственном электрическом коконе.", true);
        stringBooleanMap.put("", false);
        stringBooleanMap.put("<div>Рис. 17 &nbsp;На упрощённой схеме шесть атомов углерода в вершинах правильного шестиугольника совместно образуют минимальную, первичную структуру элементарной молекулы графита – С6.</div>", false);
        return stringBooleanMap;
    }
}