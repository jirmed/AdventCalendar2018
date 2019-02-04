package net.konzult.adventcalendar2018.day12;

import java.util.Arrays;

public class GrowPattern {
    private final boolean[] pattern;

    public GrowPattern(boolean[] pattern) {
        this.pattern = pattern;
    }

    public boolean[] getPattern() {
        return pattern;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GrowPattern that = (GrowPattern) o;
        return Arrays.equals(pattern, that.pattern);
    }

    @Override
    public int hashCode() {
        return Arrays.hashCode(pattern);
    }
}
