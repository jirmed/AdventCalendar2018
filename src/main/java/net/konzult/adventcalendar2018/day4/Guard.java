package net.konzult.adventcalendar2018.day4;

import java.util.Objects;

public class Guard {
    private final int id;
    private int[] minutes = new int[59];

    public Guard(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public int[] getMinutes() {
        return minutes;
    }

    public int minuteInc(int minute) {
        return minutes[minute]++;
    }

    public SleepCount getMaxSleepCount() {

        int max = minutes[0];
        int max_minute = 0;

        for (int i = 0; i < minutes.length; i++) {
            if (max < minutes[i]) {
                max = minutes[i];
                max_minute = i;
            }
        }
        return new SleepCount(max_minute, max);
    }

    @Override
    public String toString() {
        return "Guard{" +
                "id=" + id +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Guard guard = (Guard) o;
        return id == guard.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public class SleepCount {
        private final int minute;
        private final int count;

        public SleepCount(int minute, int count) {
            this.minute = minute;
            this.count = count;
        }

        public int getMinute() {
            return minute;
        }

        public int getCount() {
            return count;
        }

        @Override
        public String toString() {
            return "SleepCount{" +
                    "minute=" + minute +
                    ", count=" + count +
                    '}';
        }
    }
}
