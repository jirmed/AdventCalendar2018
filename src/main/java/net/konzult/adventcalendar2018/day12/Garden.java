package net.konzult.adventcalendar2018.day12;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Garden {
    private static final int PATTERN_WIDTH = 5;
    private final Map<GrowPattern, Boolean> growPatternMap = new HashMap<>();
    private Set<Integer> plantsSet = new TreeSet<>();

    Map<GrowPattern, Boolean> getGrowPatternMap() {
        return growPatternMap;
    }

    Set<Integer> getPlantsSet() {
        return plantsSet;
    }

    GrowPattern getGrowPatternAt(int position) {
        boolean[] patternArray = new boolean[PATTERN_WIDTH];
        for (int i = 0; i < PATTERN_WIDTH; i++) {
            patternArray[i] = plantsSet.contains(position - PATTERN_WIDTH / 2 + i);
        }
        return new GrowPattern(patternArray);
    }

    Garden nextGeneration() {
        TreeSet<Integer> nextDayPantsSet = new TreeSet<>();
        for (int i = plantsSet.stream().min(Integer::compareTo).get() - PATTERN_WIDTH / 2;
             i <= plantsSet.stream().max(Integer::compareTo).get() + PATTERN_WIDTH / 2; i++) {
            Boolean willGrow = growPatternMap.get(getGrowPatternAt(i));
            if (willGrow != null && willGrow)
                nextDayPantsSet.add(i);
        }
        plantsSet = nextDayPantsSet;
        return this;
    }

    public Garden playGenerations(long generations) {

        for (long i = 0; i < generations; i++) {
            nextGeneration();
        }
        return this;
    }

    public long getCheckumAfterManyGenerations(long generations) {
        Map<Plants, ShiftedPlants> usedPlantsMap = new HashMap<>();
        for (long generation = 0; generation < generations; generation++) {
            ShiftedPlants currentShiftedPlants = ShiftedPlants.build(plantsSet, generation);
            ShiftedPlants usedShiftedPlants = usedPlantsMap.get(currentShiftedPlants.getPlants());
            if (usedShiftedPlants == null) {
                usedPlantsMap.put(currentShiftedPlants.getPlants(), currentShiftedPlants);
                nextGeneration();
            } else {
                long cycleLength = generation - usedShiftedPlants.getGeneration();
                int cycleShiftedBy = currentShiftedPlants.getShiftBy()
                        - usedShiftedPlants.getShiftBy();
                long generationsRemaining = generations - generation;
                long cyclesRemaining = generationsRemaining / cycleLength;
                long generationOffset = generationsRemaining % cycleLength;
                if (generationOffset > 0 ) {
                    playGenerations(generationOffset);
                    currentShiftedPlants = ShiftedPlants.build(plantsSet, generation + generationOffset);
                }
                return getCheckSum(currentShiftedPlants)
                        + cyclesRemaining*cycleShiftedBy*currentShiftedPlants.getPlants().size();
            }
        }
        return getCheckSum();

    }

    private int getCheckSum(Set<Integer> plantsSet) {
        int checkSum = 0;
        for (Integer plantNo : plantsSet) checkSum += plantNo;
        return checkSum;
    }

    public int getCheckSum() {
        return getCheckSum(plantsSet);
    }

    int getCheckSum(ShiftedPlants shiftedPlants) {
        return getCheckSum(shiftedPlants.getPlants().getPlantsSet())
                + shiftedPlants.getPlants().size() * shiftedPlants.shiftBy;
    }

    public static Garden readGardenFile(String fileName) throws IOException {
        Garden garden = new Garden();
        List<String> strings = FileParser.readStringListFile(fileName);
        garden.setInitialPlants(strings.get(0));
        garden.initGrowPatterns(strings);
        return garden;
    }

    private void initGrowPatterns(List<String> strings) {
        Pattern pattern = Pattern.compile("^([#|.]{5}) => ([#|.])$");
        for (int i = 2; i < strings.size(); i++) {
            String s = strings.get(i);
            Matcher matcher = pattern.matcher(s);
            if (matcher.find()) {
                String patternString = matcher.group(1);
                boolean[] patternArray = new boolean[PATTERN_WIDTH];
                for (int j = 0; j < PATTERN_WIDTH; j++) {
                    patternArray[j] = (patternString.charAt(j) == '#');
                }
                this.getGrowPatternMap().put(new GrowPattern(patternArray)
                        , matcher.group(2).equals("#"));
            } else
                throw new IllegalArgumentException("Incorrect format");
        }
    }

    private void setInitialPlants(String s) {
        String initialString;
        Pattern pattern = Pattern.compile("^initial state:\\s*([#|.]*)$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find()) {
            initialString = matcher.group(1);
        } else
            throw new IllegalArgumentException("Illegal format");

        Set<Integer> plantsSet = this.getPlantsSet();
        for (int i = 0; i < initialString.length(); i++) {
            if (initialString.charAt(i) == '#')
                plantsSet.add(i);
        }
    }

    public static class Plants {
        private final Set<Integer> plantsSet;

        Plants(Set<Integer> plantsSet) {
            this.plantsSet = plantsSet;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Plants plants1 = (Plants) o;
            return plantsSet.equals(plants1.plantsSet);
        }

        @Override
        public int hashCode() {
            return Objects.hash(plantsSet);
        }

        Set<Integer> getPlantsSet() {
            return plantsSet;
        }

        public int size() {
            return plantsSet.size();
        }
    }

    static class ShiftedPlants {
        private final Plants plants;
        private final int shiftBy;
        private final long generation;

        private ShiftedPlants(Plants plants, int shiftBy, long generation) {
            this.plants = plants;
            this.shiftBy = shiftBy;
            this.generation = generation;
        }

        static ShiftedPlants build(Set<Integer> plantsSet, long generation) {
            TreeSet<Integer> shiftedPlantSet = new TreeSet<>();
            int shiftBy = plantsSet.stream().min(Integer::compareTo).get();
            plantsSet.forEach(plant -> shiftedPlantSet.add(plant - shiftBy));
            return new ShiftedPlants(new Plants(shiftedPlantSet), shiftBy, generation);
        }

        Plants getPlants() {
            return plants;
        }

        int getShiftBy() {
            return shiftBy;
        }

        long getGeneration() {
            return generation;
        }
    }

}
