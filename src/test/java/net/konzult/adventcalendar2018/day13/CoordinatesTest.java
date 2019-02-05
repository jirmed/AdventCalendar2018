package net.konzult.adventcalendar2018.day13;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CoordinatesTest {

    @Test
    void testCompareTo() throws Exception {
        Coordinates c = new Coordinates(10, 10);

        assertThat(c.compareTo(new Coordinates(11,11))).isLessThan(0);
    }
}