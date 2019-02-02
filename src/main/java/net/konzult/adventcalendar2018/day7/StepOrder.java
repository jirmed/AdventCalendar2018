package net.konzult.adventcalendar2018.day7;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StepOrder {
    private final char first, second;

    public StepOrder(char first, char second) {
        this.first = first;
        this.second = second;
    }

    public char getFirst() {
        return first;
    }

    public char getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StepOrder stepOrder = (StepOrder) o;
        return first == stepOrder.first &&
                second == stepOrder.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public String toString() {
        return "StepOrder{" +
                "first=" + first +
                ", second=" + second +
                '}';
    }

    public static List<StepOrder> readStepOrderListFile(String fileName) throws IOException {
        List<StepOrder> stepOrders = new ArrayList<>();
        List<String> strings = FileParser.readStringListFile(fileName);
        Pattern pattern = Pattern.compile("^Step ([A-Z]) must be finished before step ([A-Z]) can begin\\.$");
        for (String stepOrderString : strings) {
            Matcher matcher= pattern.matcher(stepOrderString);
            if (!matcher.find())
                throw new IllegalArgumentException("Cannot parse");
            stepOrders.add(new StepOrder(matcher.group(1).charAt(0),matcher.group(2).charAt(0)));
        }
        return stepOrders;
    }
}
