package net.konzult.adventcalendar2018.day4;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class LogItemTest {

    @Test
    void testFactory() throws Exception {
        assertThat(LogItem.factory("[1518-04-21 00:57] wakes up"))
                .isEqualTo(new LogItem(LocalDateTime.of(1518, Month.APRIL, 21, 0, 57, 0),
                        GuardAction.AWAKE, 0));
        assertThat(LogItem.factory("[1518-09-03 00:12] falls asleep"))
                .isEqualTo(new LogItem(LocalDateTime.of(1518, Month.SEPTEMBER, 3, 0, 12, 0),
                        GuardAction.SLEEP, 0));
        assertThat(LogItem.factory("[1518-04-21 00:04] Guard #3331 begins shift"))
                .isEqualTo(new LogItem(LocalDateTime.of(1518, Month.APRIL, 21, 0, 4, 0),
                        GuardAction.GUARD, 3331));

    }


    @Test
    void testReadLogItemListFile() throws Exception{
        List<LogItem> logItems = LogItem.readLogItemListFile("day4test.txt");
        assertThat(logItems.size()).isEqualTo(17);
    }
}