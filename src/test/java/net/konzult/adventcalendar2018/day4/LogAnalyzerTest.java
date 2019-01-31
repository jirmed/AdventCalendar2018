package net.konzult.adventcalendar2018.day4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LogAnalyzerTest {

    @Test
    void testGetMaxSleepGuardByMaxMinute() {

        LogAnalyzer logAnalyzer = createLogAnalyzerTestData();

        assertThat(logAnalyzer.getMaxSleepGuardByMaxMinute().getMaxSleepCount())
                .extracting("minute","count")
                .contains(9,3);

    }

    @Test
    void testGetMaxSleepGuardByTotalTime() throws Exception {
        LogAnalyzer logAnalyzer = createLogAnalyzerTestData();

        assertThat(logAnalyzer.getMaxSleepGuardByTotalTime())
                .extracting("id","totalSleepTime")
                .contains(2,3);
    }

    private LogAnalyzer createLogAnalyzerTestData() {
        LogAnalyzer logAnalyzer;
        Guard guard1 = new Guard(1);
        guard1.minuteInc(15);
        guard1.minuteInc(15);

        Guard guard2 = new Guard(2);
        guard2.minuteInc(9);
        guard2.minuteInc(9);
        guard2.minuteInc(9);

        Guard guard3 = new Guard(3);

        List<Guard> guards = Arrays.asList(guard1, guard2, guard3);

        logAnalyzer = new LogAnalyzer(null);

        Map<Integer, Guard> guardsMap = logAnalyzer.getGuards();
        guards.forEach(guard -> guardsMap.put(guard.getId(), guard));
        return logAnalyzer;
    }

    @Test
    void testProcess() throws Exception {
        List<LogItem> logItems = LogItem.readLogItemListFile("day4test.txt");
        LogAnalyzer logAnalyzer = new LogAnalyzer(logItems);
        logAnalyzer.process();
        Map<Integer, Guard> guards = logAnalyzer.getGuards();

        assertThat(guards.size()).isEqualTo(2);
        Guard guard99 = guards.get(99);
        assertThat(guard99.getMinutes()[45]).isEqualTo(3);

        Guard maxSleepGuard = logAnalyzer.getMaxSleepGuardByTotalTime();
        assertThat(maxSleepGuard.getId()).isEqualTo(10);

    }


}
