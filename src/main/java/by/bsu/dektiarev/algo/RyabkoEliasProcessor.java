package by.bsu.dektiarev.algo;

import com.google.common.base.Splitter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by USER on 27.11.2016.
 */
public final class RyabkoEliasProcessor {

    private static final char[] ALPHABET = new char[] {'0', '1'};

    public static List<String> encodeText(String text, int phraseLength) {
        LinkedList<String> allPhrases = buildPhraseList(phraseLength);
        List<String> splitStrings = Splitter.fixedLength(phraseLength).splitToList(text);
        List<String> code = new ArrayList<>();

        List<String> phrases = new ArrayList<>(splitStrings);

        //adding spaces to the last string
        String last = phrases.get(phrases.size() - 1);
        while(last.length() != phraseLength) {
            last += '0';
        }
        phrases.set(phrases.size() - 1, last);

        for(String phrase : phrases) {
            int index = allPhrases.indexOf(phrase);
            allPhrases.remove(index);
            allPhrases.addFirst(phrase);
            code.add(buildEliasCode(index));
        }
        return code;
    }

    public static double getMedianPhraseLength(List<String> phrases) {
        double length = 0;
        for(String phrase : phrases) {
            length += phrase.length();
        }
        return length / phrases.size();
    }

    private static LinkedList<String> buildPhraseList(Integer phraseLength) {
        LinkedList<String> phraseList = new LinkedList<>();
        int currentVariant[] = new int[phraseLength];

        outer:
        while (true) {
            StringBuilder phrase = new StringBuilder();

            for (int ndx : currentVariant) {
                phrase.append(ALPHABET[ndx]);
            }

            phraseList.add(phrase.toString());
            int i = phraseLength - 1; // указатель в самую правую ячейку

            while (currentVariant[i] == ALPHABET.length - 1) { //движемся влево, если ячейка переполнена
                currentVariant[i] = 0; //записываем в ячейку 0, т.к. идет перенос разряда
                i--; //сдвиг влево
                // если перенос влево невозможен, значит перебор закончен
                if (i < 0) {
                    break outer;
                }
            }

            currentVariant[i]++; //увеличиваем значение ячейки на единицу
        }

        return phraseList;
    }

    private static String buildEliasCode(int number) {
        String binary = Integer.toBinaryString(number).substring(1);
        int length = binary.length() + 1;
        StringBuilder binaryLength = new StringBuilder(Integer.toBinaryString(length));
        int zerosToAdd = binaryLength.length() - 1;
        for(int i = 0; i < zerosToAdd; i++) {
            binaryLength.insert(0, "0");
        }
        binaryLength.append(binary);
        return binaryLength.toString();
    }


    private RyabkoEliasProcessor() {
    }
}
