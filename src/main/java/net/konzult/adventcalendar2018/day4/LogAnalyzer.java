package net.konzult.adventcalendar2018.day4;

import java.time.LocalDateTime;
import java.util.*;

public class LogAnalyzer {
    private final Map<Integer, Guard> guards = new HashMap<Integer, Guard>();
    private List<LogItem> log;

    public LogAnalyzer(List<LogItem> log) {
        this.log = log;
    }

    public void process() {
        log.sort(LogItem::compareTo);
        Guard guard = null;
        boolean sleeps = false;
        LocalDateTime lastSleep = null;
        for (int i = 0; i < log.size(); i++) {
            LogItem logItem = log.get(i);
            switch (logItem.getAction()) {
                case GUARD:
                    guard = guards.getOrDefault(logItem.getGuardId(),
                            new Guard(logItem.getGuardId()));
                    guards.put(guard.getId(),guard);
                    sleeps = false;
                    break;
                case SLEEP:
                    sleeps = true;
                    lastSleep = logItem.getDateTime();
                    break;
                case AWAKE:
                    sleeps = false;
                    if (logItem.getDateTime().toLocalDate().equals(lastSleep.toLocalDate())
                            && logItem.getDateTime().getHour() == 0
                            && lastSleep.getHour() == 0) {
                        for (int minute = lastSleep.getMinute();
                             minute < logItem.getDateTime().getMinute(); minute++) {
                            guard.minuteInc(minute);
                        }
                    }
            }

        }
    }

    public Guard getMaxSleepGuardByMaxMinute() {
        Guard.SleepCount maxSleepCount = null;
        Guard maxGuard = null;
        for (Guard guard : guards.values()
        ) {
            Guard.SleepCount currentGuardMaxSleepCount = guard.getMaxSleepCount();
            if (maxSleepCount == null ||
                    currentGuardMaxSleepCount.getCount() > maxSleepCount.getCount()) {
                maxSleepCount = currentGuardMaxSleepCount;
                maxGuard = guard;
            }
        }
        return maxGuard;
    }

    public Guard getMaxSleepGuardByTotalTime() {
        return guards.values().stream().max(Comparator.comparing(Guard::getTotalSleepTime)).get();
    }

    public List<LogItem> getLog() {
        return log;
    }

    public Map<Integer, Guard> getGuards() {
        return guards;
    }
}
