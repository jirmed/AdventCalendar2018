package net.konzult.adventcalendar2018.day18;

import net.konzult.adventcalendar2018.FileParser;
import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static net.konzult.adventcalendar2018.day18.AcreType.*;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class CollectionAreaTest {

    @Test
    void testParseMap() throws Exception {
        List<String> strings = FileParser.readStringListFile("day18test.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(strings);
        assertThat(area.getAreaMap()).hasSize(100);
    }


    @Test
    void testCountNeibourghType() throws Exception {
        List<String> strings = FileParser.readStringListFile("day18test.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(strings);
        assertThat(area.countNeibourghType(new Coordinates(8,1), LUMBER_YARD)).isEqualTo(3);
        assertThat(area.countNeibourghType(new Coordinates(8,1), TREE)).isEqualTo(2);
    }

    @ParameterizedTest
    @CsvSource({"8,1,LUMBER_YARD",
    "0,0,GROUND",
    "1,2,TREE"})
    void testGetNewType(int x, int y, AcreType type) throws Exception {
        List<String> strings = FileParser.readStringListFile("day18test.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(strings);
        assertThat(area.getNewType(new Coordinates(x,y))).isEqualTo(type);
    }

    @Test
    void testRender() throws Exception {
        List<String> strings = FileParser.readStringListFile("day18test.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(strings);
        List<String> render = area.render();
        for (String s : render) {
            System.out.println(s);
        }
        assertThat(render).isEqualTo(strings);
    }

    @Test
    void testPlayMinute() throws Exception {
        List<String> input = FileParser.readStringListFile("day18test.txt");
        List<String> expectedOutput = FileParser.readStringListFile("day18minute1.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(input);
        area.playMinute();
        List<String> render = area.render();
        assertThat(render).isEqualTo(expectedOutput);
    }

    @ParameterizedTest
    @CsvSource({"1,day18minute1.txt",
            "2,day18minute2.txt",
            "10,day18minute10.txt"})
    void testPlayMinutes(int minutes, String fileName) throws Exception {
        List<String> input = FileParser.readStringListFile("day18test.txt");
        List<String> expectedOutput = FileParser.readStringListFile(fileName);
        CollectionArea area = new CollectionArea();
        area.parseMap(input);
        area.playMinutes(minutes);
        List<String> render = area.render();
        assertThat(render).isEqualTo(expectedOutput);

    }

    @Test
    void testGetResourceValue() throws Exception {
        List<String> input = FileParser.readStringListFile("day18.txt");
        CollectionArea area = new CollectionArea();
        area.parseMap(input);
        area.playMinutes(1000);
        assertThat(area.getResourceValue()).isEqualTo(1147);
    }

    @ParameterizedTest
    @CsvSource({"1","10","500","1000"})
    void testPlayMinutesWithHistory(long minutes) throws Exception {
        List<String> input = FileParser.readStringListFile("day18.txt");
        CollectionArea area = new CollectionArea();
        CollectionArea referenceArea = new CollectionArea();
        area.parseMap(input);
        referenceArea.parseMap(input);
        for (int i = 0; i < minutes ; i++) {
            referenceArea.playMinute();
        }
        area.playMinutes(minutes);
        assertThat(area.getResourceValue()).isEqualTo(referenceArea.getResourceValue());

    }
}