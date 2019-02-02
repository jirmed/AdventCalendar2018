package net.konzult.adventcalendar2018.day9;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.shadow.com.univocity.parsers.csv.Csv;

import java.util.Arrays;
import java.util.regex.Matcher;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class MarbleGameTest {

    @Test
    void testTurn() throws Exception {
        MarbleGame marbleGame = new MarbleGame(9);
        for (int i = 0; i < 5; i++) {
            marbleGame.turn();
        }
        assertThat(marbleGame.getCurrentMarble()).isEqualTo(3);
        assertThat(marbleGame.getMarbleWorth()).isEqualTo(5);
        assertThat(marbleGame.getMarbles()).isEqualTo(Arrays.asList(0, 4, 2, 5, 1, 3));
    }

    @Test
    void testPlay() throws Exception {
        MarbleGame marbleGame = new MarbleGame(9);
        marbleGame.play(25);

        assertThat(marbleGame.getMarbles().size()).isEqualTo(24);
        assertThat(marbleGame.getMarbles())
                .isEqualTo(Arrays.asList(0, 16, 8, 17, 4, 18, 19, 2, 24, 20, 25, 10, 21, 5, 22, 11, 1, 12, 6, 13, 3, 14, 7, 15));
        assertThat(marbleGame.getMaxScore()).isEqualTo(32);
    }

    @ParameterizedTest
    @CsvSource({"9,25,32",
            "10,1618,8317",
            "13,7999,146373",
            "21,6111,54718",
            "30,5807,37305"
    })
    void testName(int playerCount, int maxMarbleWorth, int maxScore) throws Exception {
        MarbleGame marbleGame = new MarbleGame(playerCount);
        marbleGame.play(maxMarbleWorth);

        assertThat(marbleGame.getMaxScore()).isEqualTo(maxScore);
    }
}