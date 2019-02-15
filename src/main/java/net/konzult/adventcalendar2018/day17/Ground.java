package net.konzult.adventcalendar2018.day17;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static net.konzult.adventcalendar2018.day17.Substance.*;

public class Ground {

    private final Map<Coordinates, Substance> groundMap = new TreeMap<>();
    private int minY;
    private int maxY;
    private int counter = 0;
    private boolean debug = false;

    private final Map<Coordinates, Integer> trace = new HashMap<>();

    public Map<Coordinates, Substance> getGroundMap() {
        return groundMap;
    }

    public void parseGroundList(List<String> strings) {
        Pattern pattern = Pattern.compile("^([x|y])=(\\d+),\\s*[x|y]=(\\d+)..(\\d+)\\s*$");
        for (String s : strings) {
            Matcher matcher = pattern.matcher(s);
            if (!matcher.find())
                throw new IllegalArgumentException("Invalid format");
            for (int i = Integer.parseInt(matcher.group(3));
                 i <= Integer.parseInt(matcher.group(4)); i++) {
                if (matcher.group(1).equals("x"))
                    groundMap
                            .put(new Coordinates(Integer.parseInt(matcher.group(2)), i), Substance.CLAY);
                else
                    groundMap
                            .put(new Coordinates(i, Integer.parseInt(matcher.group(2))), Substance.CLAY);
            }
        }
        minY = groundMap.keySet().stream().mapToInt(Coordinates::getY).min().orElse(0);
        maxY = groundMap.keySet().stream().mapToInt(Coordinates::getY).max().orElse(0);
    }

    public boolean pour(Coordinates position) {
        if (debug)
            System.out.println(position);
//        Integer posCount = trace.get(position);
//        if (posCount == null) {
//            trace.put(position, 1);
//        } else if (posCount > 10) {
//            debug = true;
//        } else trace.put(position, posCount + 1);
//        if (counter++ > 10000) return false;
        if (position.getY() > maxY)
            return false;
        if (position.getY() < minY)
            return pour(position.down());
        Substance substanceAtPosition = groundMap.get(position);
        if (isSolid(substanceAtPosition))
            return true;
        else if (substanceAtPosition == null)
            groundMap.putIfAbsent(position, WATER);
//        Coordinates bellowPosition = position.down();
        Substance belowSubstance = groundMap.get(position.down());
        if (false /*belowSubstance == WATER*/) return false;
        else if (!isSolid(belowSubstance))
            if (pour(position.down())) {
                groundMap.remove(position);
                return pour(position);
            } else {
                return false;
            }
        else {
            return (flood(position));
        }
    }

    private boolean flood(Coordinates position) {
        boolean canFlood = true;
        for (Coordinates pos = position; !isSolid(groundMap.get(pos)); pos = pos.left()) {
            if (!floodStep(pos)) {
                canFlood = false;
                break;
            }
        }

        for (Coordinates pos = position.right(); !isSolid(groundMap.get(pos)); pos = pos.right()) {
            if (!floodStep(pos)) {
                canFlood = false;
                break;
            }
        }

        if (canFlood) {
            for (Coordinates pos = position.left(); groundMap.get(pos) == WATER; pos = pos.left()) {
                groundMap.put(pos, STILL_WATER);
            }
            for (Coordinates pos = position; groundMap.get(pos) == WATER; pos = pos.right()) {
                groundMap.put(pos, STILL_WATER);
            }
            return true;
        } else return false;

    }

    private boolean floodStep(Coordinates pos) {
        boolean canFlood = true;
        groundMap.put(pos, WATER);
        if (!isSolid(groundMap.get(pos.down()))) {
            if (groundMap.get(pos.down()) == null)
                canFlood = pour(pos.down());
            else canFlood = false;
        }
        return canFlood;
    }

    private boolean isSolid(Substance substance) {
        return substance == CLAY || substance == STILL_WATER;
    }

    public long getTileCount(Substance substance) {
        return groundMap.entrySet().stream()
                .filter(entry -> entry.getValue() == substance)
                .count();
    }


    public List<String> render() {
        int minX = groundMap.keySet().stream().mapToInt(Coordinates::getX).min().orElse(0);
        int maxX = groundMap.keySet().stream().mapToInt(Coordinates::getX).max().orElse(0);
        List<String> output = new ArrayList<>();
        for (int y = minY - 1; y <= maxY + 1; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = minX - 1; x <= maxX + 1; x++) {
                Substance substance = groundMap.get(new Coordinates(x, y));
                if (substance == CLAY)
                    stringBuilder.append("#");
                else if (substance == WATER)
                    stringBuilder.append("|");
                else if (substance == STILL_WATER)
                    stringBuilder.append("~");
                else stringBuilder.append(" ");
            }
            output.add(stringBuilder.toString());
        }
        return output;
    }
}
