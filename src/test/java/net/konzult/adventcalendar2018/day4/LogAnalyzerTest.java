package net.konzult.adventcalendar2018.day4;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.tuple;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LogAnalyzerTest {

    @Test
    void getMaxSleepGuard() {
        Guard guard1 = new Guard(1);
        guard1.minuteInc(15);
        guard1.minuteInc(15);

        Guard guard2 = new Guard(2);
        guard2.minuteInc(9);
        guard2.minuteInc(9);
        guard2.minuteInc(9);

        Guard guard3 = new Guard(3);

        List<Guard> guards = Arrays.asList(guard1, guard2, guard3);

        LogAnalyzer logAnalyzer = new LogAnalyzer(null);

        Map<Integer, Guard> guardsMap = logAnalyzer.getGuards();
        guards.forEach(guard -> guardsMap.put(guard.getId(), guard));

        assertThat(logAnalyzer.getMaxSleepGuard().getMaxSleepCount())
                .extracting("minute","count")
                .contains(9,3);

    }

}
