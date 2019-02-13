package net.konzult.adventcalendar2018.day17;

import net.konzult.adventcalendar2018.FileParser;
import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GroundTest {

    @Test
    void testRender() throws Exception {
        List<String> strings = FileParser.readStringListFile("day17.txt");
        Ground ground = new Ground();
        ground.parseGroundList(strings);
        ground.pour(new Coordinates(500,0));
        List<String> render = ground.render();
        for (String s : render) {
            System.out.println(s);
        }
        System.out.println(ground.getTileCount(Substance.WATER) + ground.getTileCount(Substance.STILL_WATER));
        System.out.println( ground.getTileCount(Substance.STILL_WATER));
    }
}