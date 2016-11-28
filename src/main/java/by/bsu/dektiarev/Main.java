package by.bsu.dektiarev;

import by.bsu.dektiarev.algo.RyabkoEliasProcessor;
import by.bsu.dektiarev.util.TextLoader;
import org.knowm.xchart.QuickChart;
import org.knowm.xchart.SwingWrapper;
import org.knowm.xchart.XYChart;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by USER on 25.11.2016.
 */
public class Main {

    private static final String FILENAME = "input.txt";

    public static void main(String[] args) {

        String text = TextLoader.loadText(FILENAME);
        String encodedText = TextLoader.convertToBinary(text);

        List<Double> xCoords = new ArrayList<>();
        List<Double> ycoords = new ArrayList<>();


        int phraseLength = 2;

        while(phraseLength != 16) {
            List<String> code = RyabkoEliasProcessor.encodeText(encodedText, phraseLength);
            double medianPhraseLength = RyabkoEliasProcessor.getMedianPhraseLength(code);
            System.out.println("Length for " + phraseLength + ": " + medianPhraseLength);

            xCoords.add((double) phraseLength);
            ycoords.add(medianPhraseLength);

            phraseLength++;
        }

        XYChart chart = QuickChart.getChart("Result", "Phrase length",
                "Median code word length", "chart", xCoords, ycoords);
        new SwingWrapper(chart).displayChart();
    }
}
