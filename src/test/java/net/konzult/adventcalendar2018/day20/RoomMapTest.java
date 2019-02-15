package net.konzult.adventcalendar2018.day20;

import net.konzult.adventcalendar2018.day13.Coordinates;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class RoomMapTest {


    @Test
    void testSplitter() throws Exception {
        assertThat(RoomMap.splitter("a|b|c(d|e(f|g))|h|", '|')).hasSize(10);

    }

    @Test
    void testTraceAllPaths() throws Exception {
        RoomMap roomMap = new RoomMap();
        roomMap.traceAllPaths(Coordinates.ZERO,"^ENNWSWW(NEWS|)SSSEEN(WNSE|)EE(SWEN|)NNN$");
        assertThat(roomMap.doorsEast).hasSize(11);
        assertThat(roomMap.doorsSouth).hasSize(13);
    }

    @Test
    void testPopulateDistanceMap() throws Exception {
        RoomMap roomMap = new RoomMap();
        roomMap.traceAllPaths(Coordinates.ZERO,"^WSSEESWWWNW(S|NENNEEEENN(ESSSSW(NWSW|SSEN)|WSWWN(E|WWS(E|SS))))$");
        roomMap.populateDistanceMap();
        assertThat(roomMap.getMaxDistance()).isEqualTo(31);
        assertThat(roomMap.countDoorsInDistanceAtLeast(10)).isEqualTo(39);
    }
}