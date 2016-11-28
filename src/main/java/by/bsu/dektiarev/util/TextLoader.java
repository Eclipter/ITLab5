package by.bsu.dektiarev.util;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.StringJoiner;

/**
 * Created by USER on 27.11.2016.
 */
public final class TextLoader {

    public static String loadText(String filename) {
        try {
            Path path = Paths.get(TextLoader.class.getResource("/" + filename).toURI());
            List<String> strings = Files.readAllLines(path);
            StringJoiner joiner = new StringJoiner(" ");
            strings.forEach(joiner::add);
            String text = joiner.toString();
            text = text.replaceAll("ё", "е");
            text = text.replaceAll("ъ", "ь");
            text = text.replaceAll(",|\\.|-|;|:|\\?|!|\n|\t|0|1|2|3|4|5|6|7|8|9|0|\\(|\\)", "");
            text = text.toLowerCase();
            return text;
        } catch (IOException | URISyntaxException e) {
            throw new RuntimeException(e);
        }
    }

    public static String convertToBinary(String text) {
        char[] chars = text.toCharArray();
        StringBuilder encodedText = new StringBuilder();
        for(char symbol : chars) {
            encodedText.append(Integer.toBinaryString(symbol));
        }
        return encodedText.toString();
    }

    private TextLoader() {
    }
}
