package net.konzult.adventcalendar2018.day13;

import net.konzult.adventcalendar2018.FileParser;
import org.junit.jupiter.api.Test;

import java.util.List;

//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.Assertions.*;

class TrackChartTest {

    @Test
    void testParseTrack() throws Exception {
        List<String> strings = FileParser.readStringListFile("day13test.txt");
        TrackChart trackChart = new TrackChart();
        trackChart.parseTrack(strings);

        assertThat(trackChart.getTrackMap().size()).isEqualTo(46);
        assertThat(trackChart.getCarts().size()).isEqualTo(2);

    }

    @Test
    void testTick() throws Exception {
        List<String> strings = FileParser.readStringListFile("day13test.txt");
        TrackChart trackChart = new TrackChart();
        trackChart.parseTrack(strings);

        for (int i = 0; i < 10; i++) {
            trackChart.tick();
        }

        assertThat(trackChart.getCarts()).extracting("position")
                .containsOnly(new Coordinates(9,3),
                        new Coordinates(9,1));
    }

    @Test
    void testTickUntilCrash() throws Exception {
        List<String> strings = FileParser.readStringListFile("day13test.txt");
        TrackChart trackChart = new TrackChart();
        trackChart.parseTrack(strings);

        assertThat(trackChart.tickUntilCrash()).isEqualTo(new Coordinates(7,3));
        assertThat(trackChart.getCarts()).isEmpty();
    }

    @Test
    void testTickUntilLastRemains() throws Exception {
        List<String> strings = FileParser.readStringListFile("day13test2.txt");
        TrackChart trackChart = new TrackChart();
        trackChart.parseTrack(strings);

        assertThat(trackChart.tickUntilLastRemains())
                .isEqualTo(new Coordinates(6,4));;
    }
}