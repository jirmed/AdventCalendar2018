package net.konzult.adventcalendar2018.day14;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.*;


class ChocolateChartTest {

    @Test
    void testRound() throws Exception {
        ChocolateChart chart = new ChocolateChart();

        for (int i = 0; i < 15; i++) {
            chart.round();
        }

        assertThat(chart.getRecipes())
                .hasSize(20)
                .isEqualTo(Arrays.asList(3, 7, 1, 0, 1, 0, 1, 2, 4, 5, 1, 5, 8, 9, 1, 6,  7,  7,  9,  2));
    }

    @ParameterizedTest
    @CsvSource({"9,5158916779",
    "5,0124515891",
    "18,9251071085",
    "2018,5941429882"})
    void testScoreTenAfter(int skip, String score) throws Exception {
        ChocolateChart chart = new ChocolateChart();

        assertThat(chart.scoreTenAfter(skip)).isEqualTo(score);
    }

    @ParameterizedTest
    @CsvSource({
            "51589,9",
            "01245,5",
            "92510,18",
            "59414,2018"
    })
    void testFindRecipe(String recipe, int position) throws Exception {
        ChocolateChart chart = new ChocolateChart();

        assertThat(chart.findRecipe(recipe)).isEqualTo(position);
    }
}