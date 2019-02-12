package net.konzult.adventcalendar2018.day16;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Tester {
    private final Map<Integer, Instruction> instructionMap = new HashMap<>();

    public Tester(List<Sample> samples) {
        this.samples = samples;
    }

    static class Sample {
        final int[] input;
        final int[] output;
        final int[] operation;

        Sample(int[] input, int[] operation, int[] output) {
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
        Pattern patternBefore = Pattern.compile("^Before:\\s*\\[(\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d+)\\]$");
        Pattern patternAfter = Pattern.compile("^After:\\s*\\[(\\d+),\\s*(\\d+),\\s*(\\d+),\\s*(\\d+)\\]$");
        Pattern patternOperation = Pattern.compile("^(\\d+)\\s+(\\d+)\\s+(\\d+)\\s+(\\d+)$");

        for (int i = 0; i < strings.size() / 4; i++) {
            int[] before = matchIntArray(patternBefore, strings.get(i * 4));
            int[] operation = matchIntArray(patternOperation, strings.get(i * 4 + 1));
            int[] after = matchIntArray(patternAfter, strings.get(i * 4 + 2));
            samples.add(new Sample(before, operation, after));
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
        }
        return array;
    }

    public static List<Instruction> getValidInstructionList(Sample sample) {
        ArrayList<Instruction> instructions = new ArrayList<>();
        Processor processor = new Processor();
        for (Instruction instruction : Instruction.values()) {
            processor.setReg(sample.input.clone());
            int[] output = processor.operation(instruction, sample.operation[1], sample.operation[2], sample.operation[3]);
            if (Arrays.equals(output, sample.output))
                instructions.add(instruction);
        }
        return instructions;
    }

    public long countSamplesWithValidInstructions(int minInstructionCount) {
        return samples.stream()
                .filter(sample -> Tester.getValidInstructionList(sample).size() >= minInstructionCount)
                .count();
    }

    public Map<Integer, Instruction> createInstructionMap() {
        instructionMap.clear();
        boolean completed = false;
        while (!completed) {
            completed = true;
            for (Sample sample : samples) {
                List<Instruction> validInstructionList = Tester.getValidInstructionList(sample).stream()
                        .filter(instruction -> !instructionMap.values().contains(instruction))
                        .collect(Collectors.toList());
                if (validInstructionList.size() == 1) {
                    instructionMap.putIfAbsent(sample.operation[0], validInstructionList.get(0));
                    completed = false;
                }
            }
        }
        return instructionMap;
    }

    public Map<Integer, Instruction> getInstructionMap() {
        return instructionMap;
    }
}