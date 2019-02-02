package net.konzult.adventcalendar2018.day6;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinateTest {

    @Test
    void testReadCoordinateListFile() throws Exception {
        List<Coordinate> coordinates = Coordinate.readCoordinateListFile("day6test.txt");

        assertThat(coordinates.size()).isEqualTo(6);
    }
}