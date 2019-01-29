package net.konzult.adventcalendar2018.day4;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LogItem implements Comparable<LogItem> {
    private final LocalDateTime dateTime;
    private final GuardAction action;
    private final int guardId;

    public LogItem(LocalDateTime dateTime, GuardAction action, int guardId) {
        this.dateTime = dateTime;
        this.action = action;
        this.guardId = guardId;
    }

    public static LogItem factory(String logString) throws IllegalAccessException {
        Pattern pattern = Pattern
                .compile("^\\[(\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2})\\] (wakes up|falls asleep|Guard #(\\d+) begins shift)$");
        Matcher matcher = pattern.matcher(logString);
        if (matcher.find()) {
            LocalDateTime dateTime = LocalDateTime.parse(matcher.group(1),
                    DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
            GuardAction action;
            int guardId = 0;
            switch (matcher.group(2)) {
                case "wakes up":
                    action = GuardAction.AWAKE;
                    break;
                case "falls asleep":
                    action = GuardAction.SLEEP;
                    break;
                default:
                    action = GuardAction.GUARD;
                    guardId = Integer.parseInt(matcher.group(3));
                    break;
            }
            return new LogItem(dateTime, action, guardId);
        } else {
            throw new IllegalAccessException("Invalid format");
        }
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public GuardAction getAction() {
        return action;
    }

    public int getGuardId() {
        return guardId;
    }

    @Override
    public String toString() {
        return "LogItem{" +
                "dateTime=" + dateTime +
                ", action=" + action +
                ", guardId=" + guardId +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        LogItem logItem = (LogItem) o;
        return guardId == logItem.guardId &&
                Objects.equals(dateTime, logItem.dateTime) &&
                action == logItem.action;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dateTime, action, guardId);
    }

    @Override
    public int compareTo(LogItem logItem) {
        return this.dateTime.compareTo(logItem.getDateTime());
    }

}
