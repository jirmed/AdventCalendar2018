package net.konzult.adventcalendar2018.day16;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tester {
    public Tester(List<Sample> samples) {
        this.samples = samples;
    }

    static class Sample {
        final int[] input;
        final int[] output;
        final int[] operation;

        Sample(int[] input, int[] output, int[] operation) {
            this.input = input;
            this.output = output;
            this.operation = operation;
        }

        @Override
        public String toString() {
            return "Sample{" +
                    "input=" + Arrays.toString(input) +
                    ", output=" + Arrays.toString(output) +
                    ", operation=" + Arrays.toString(operation) +
                    '}';
        }
    }

    private final List<Sample> samples;

    public List<Sample> getSamples() {
        return samples;
    }

    public static Tester parse(List<String> strings) {
        List<Sample> samples = new ArrayList<>();
        if (strings.size() % 4 != 0)
            throw new IllegalArgumentException("Invalid format");
        Pattern patternBefore = Pattern.compile("^Before:\\s\\[(\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d+)\\]$");
        Pattern patternAfter = Pattern.compile("^After:\\s\\[(\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d+)\\]$");
        Pattern patternOperation = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)$");

        for (int i = 0; i < strings.size() / 4; i++) {
            int[] before = matchIntArray(patternBefore, strings.get(i * 4));
            int[] operation = matchIntArray(patternOperation, strings.get(i * 4 + 1));
            int[] after = matchIntArray(patternBefore, strings.get(i * 4 + 0));
            samples.add(new Sample(before, after, operation));
        }
        return new Tester(samples);
    }

    private static int[] matchIntArray(Pattern pattern, String s) {
        int[] array = new int[4];
        Matcher matcher = pattern.matcher(s);
        if (!matcher.find())
            throw new IllegalArgumentException("Invalid format");
        for (int i = 0; i < 4; i++) {
            array[i] = Integer.parseInt(matcher.group(i + 1));
            return array;
        }
        return array;
    }
}