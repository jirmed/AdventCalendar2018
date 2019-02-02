package net.konzult.adventcalendar2018.day6;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class WorldTest {

    @Test
    void testGetMaxArea() throws Exception {

        World world;

        Coordinate a = new Coordinate(1,1);
        Coordinate b = new Coordinate(1,6);
        Coordinate c = new Coordinate(8,3);
        Coordinate d = new Coordinate(3,4);
        Coordinate e = new Coordinate(5,5);
        Coordinate f = new Coordinate(8,9);
        List<Coordinate> coordinates = Arrays.asList(a, b, c, d, e, f);

        world = new World(coordinates);

        Coordinate maxAreaCoordinate = world.getMaxAreaCoordinate();

        assertThat(maxAreaCoordinate).isEqualTo(e);
    }

    @Test
    void testGetAreaWithinTotalDistance() throws Exception {
        World world;

        Coordinate a = new Coordinate(1,1);
        Coordinate b = new Coordinate(1,6);
        Coordinate c = new Coordinate(8,3);
        Coordinate d = new Coordinate(3,4);
        Coordinate e = new Coordinate(5,5);
        Coordinate f = new Coordinate(8,9);
        List<Coordinate> coordinates = Arrays.asList(a, b, c, d, e, f);

        world = new World(coordinates);

       assertThat(world.getAreaWithinTotalDistance(32)).isEqualTo(16);
    }
}