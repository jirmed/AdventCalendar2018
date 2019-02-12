package net.konzult.adventcalendar2018.day17;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.IntStream;

import static net.konzult.adventcalendar2018.day17.Substance.CLAY;
import static net.konzult.adventcalendar2018.day17.Substance.WATER;

public class Ground {

    private final Map<Coordinates, Substance> groundMap = new TreeMap<>();
    private int minY;
    private int maxY;

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
        if (position.getY() > maxY)
            return false;
        if (position.getY() < minY)
            return pour(position.bellow());
        if (groundMap.putIfAbsent(position, WATER) != null)
            return true;
        Coordinates bellowPosition = position.bellow();
        Substance belowSubstance = groundMap.get(position.bellow());
        if (belowSubstance == null)
            if (pour(bellowPosition)) {
                groundMap.remove(position);
                return pour(position);
            } else return false;
        boolean pourLeft = pour(position.left());
        boolean pourRight = pour(position.right());
        return pourLeft && pourRight;
    }

    public long getWaterCount() {
        return groundMap.entrySet().stream()
                .filter(entry -> entry.getValue() == WATER)
                .count();
    }

    public List<String> render() {
        int minX = groundMap.keySet().stream().mapToInt(Coordinates::getX).min().orElse(0);
        int maxX = groundMap.keySet().stream().mapToInt(Coordinates::getX).max().orElse(0);
        List<String> output = new ArrayList<>();
        for (int y = minY-1; y <= maxY+1; y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = minX-1; x <= maxX+1; x++) {
                Substance substance = groundMap.get(new Coordinates(x, y));
                if (substance == CLAY)
                    stringBuilder.append("#");
                else if (substance == WATER)
                    stringBuilder.append("X");
                else stringBuilder.append(".");
            }
            output.add(stringBuilder.toString());
        }
        return output;
    }
}
