package net.konzult.adventcalendar2018.day4;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class GuardTest {

    @Test
    void minuteInc() {
        Guard guard = new Guard(1);
        guard.minuteInc(15);
        assertThat(guard.getMinutes()[15]).isEqualTo(1);
    }

    @Test
    void getMaxSleepCount() {
        Guard guard = new Guard(1);
        guard.minuteInc(14);
        guard.minuteInc(15);
        guard.minuteInc(15);
        assertThat(guard.getMaxSleepCount().getCount()).isEqualTo(2);
        assertThat(guard.getMaxSleepCount().getMinute()).isEqualTo(15);
    }
}