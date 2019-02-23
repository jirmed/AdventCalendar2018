package net.konzult.adventcalendar2018.Day22;

import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.HashSet;

import static org.assertj.core.api.Assertions.*;

class CaveTest {

    @Test
    void testGetRiskLevel() throws Exception {
        Cave cave = new Cave(new Coordinates(10, 10), 510);
        cave.init();
        assertThat(cave.getRiskLevel(new Coordinates(0,0))).isEqualTo(0);
        assertThat(cave.getRiskLevel(new Coordinates(1,0))).isEqualTo(1);
        assertThat(cave.getRiskLevel(new Coordinates(0,0))).isEqualTo(0);
        assertThat(cave.getRiskLevel(new Coordinates(1,1))).isEqualTo(2);
        assertThat(cave.getRiskLevel(new Coordinates(10,10))).isEqualTo(0);
    }

    @Test
    void testGetTotalRiskLevel() throws Exception {
        Coordinates target = new Coordinates(10, 10);
        Cave cave = new Cave(target, 510);
        cave.init();
        assertThat(cave.getTotalRiskLevel(target)).isEqualTo(114);
    }

    @Test
    void testGetShortestTimeToTarget() throws Exception {
        Coordinates target = new Coordinates(10, 10);
        Cave cave = new Cave(target, 510);
        cave.init();
        assertThat(cave.getShortestTimeToTarget()).isEqualTo(45);
    }
}