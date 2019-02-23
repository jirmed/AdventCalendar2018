package net.konzult.adventcalendar2018.Day22;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.*;

class CaveTest {

    @Test
    void testGetTotalRiskLevel() throws Exception {
        Cave cave = new Cave(10, 10, 510);
        assertThat(cave.getTotalRiskLevel()).isEqualTo(114);
    }

    @ParameterizedTest
    @CsvSource({"10,10,510,45",
            "10,11,11109,35"})
    void testGetDistanceToTarget(int targetX, int targetY, int depth, int expected) throws Exception {
        Cave cave = new Cave(targetX, targetY, depth);
        assertThat(cave.getDistanceToTarget()).isEqualTo(expected);
    }
}