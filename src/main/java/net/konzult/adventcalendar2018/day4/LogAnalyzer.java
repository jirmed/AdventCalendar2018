package net.konzult.adventcalendar2018.day4;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                    sleeps = false;
                    break;
                case SLEEP:
                    sleeps = true;
                    lastSleep = logItem.getDateTime();
                    break;
                case AWAKE:
                    sleeps = false;
                    if (logItem.getDateTime().toLocalDate() == lastSleep.toLocalDate()
                            && logItem.getDateTime().getHour() == 0
                            && lastSleep.getHour() == 0) {
                        for (int minute = logItem.getDateTime().getMinute();
                             minute < lastSleep.getMinute(); minute++) {
                            guard.minuteInc(minute);
                        }
                    }
            }

        }
    }

    public Guard getMaxSleepGuard() {
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

    public List<LogItem> getLog() {
        return log;
    }

    public Map<Integer, Guard> getGuards() {
        return guards;
    }
}
