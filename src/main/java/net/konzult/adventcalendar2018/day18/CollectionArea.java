package net.konzult.adventcalendar2018.day18;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;
import com.google.common.collect.ImmutableBiMap;
import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;

import static net.konzult.adventcalendar2018.day18.AcreType.*;

public class CollectionArea {

    private static final ImmutableBiMap<Character, AcreType> parseMap =
            ImmutableBiMap.of('.', GROUND,
                    '|',TREE,
                    '#',LUMBER_YARD);
    private Map<Coordinates, AcreType> areaMap;

    private long minute;

    public Map<Coordinates, AcreType> getAreaMap() {
        return areaMap;
    }

    public void parseMap(List<String> strings) {
        areaMap = new TreeMap<>();
        for (int y = 0; y < strings.size(); y++) {
            String s = strings.get(y);
            for (int x = 0; x < s.length(); x++) {
                areaMap.put(new Coordinates(x, y), parseMap.get(s.charAt(x)));
            }
        }
    }

    long countNeibourghType(Coordinates position, AcreType type) {
        long count = 0;
        for (int x = -1; x <=1 ; x++) {
            for (int y = -1; y <= 1; y++) {
                if ((x!=0 || y!=0) &&
                        Objects.equals(areaMap.get(position.add(new Coordinates(x, y))), type))
                    count++;
            }
        }
        return count;
    }

    AcreType getNewType(Coordinates position) {
        AcreType currentType = areaMap.get(position);
        switch (currentType) {
            case GROUND:
                return (countNeibourghType(position, TREE) >= 3 ? TREE : GROUND);
            case TREE:
                return (countNeibourghType(position, LUMBER_YARD) >= 3 ? LUMBER_YARD : TREE);
            case LUMBER_YARD:
                return (countNeibourghType(position, LUMBER_YARD) >= 1
                        && countNeibourghType(position, TREE) >= 1 ? LUMBER_YARD : GROUND);
        }
        return currentType;
    }

    void playMinute() {
        TreeMap<Coordinates, AcreType> newMap = new TreeMap<>();
        areaMap.keySet().forEach(position -> newMap.put(position, getNewType(position)));
        areaMap = newMap;

    }

    public void playMinutes(long minutes) {
        BiMap<Map<Coordinates, AcreType>, Long> historyMap = HashBiMap.create();

        for (long i = 0; i < minutes; i++) {
            historyMap.put(areaMap,minute++);
            playMinute();
            Long previous = historyMap.get(areaMap);
            if (previous != null) {
                long remains = minutes - i - 1;
                long loop = i-previous +1 ;
                if (loop>0) {
                    remains %= loop;
                    areaMap = historyMap.inverse().get(previous + remains);
                }
                return;
            }
        }
    }

    List<String> render() {
        ArrayList<String> output = new ArrayList<>();
        for (int y = 0; y <= areaMap.keySet().stream().mapToInt(Coordinates::getY).max().orElse(0); y++) {
            StringBuilder stringBuilder = new StringBuilder();
            for (int x = 0; x <= areaMap.keySet().stream().mapToInt(Coordinates::getX).max().orElse(0); x++) {
                stringBuilder.append(parseMap.inverse().get((areaMap.get(new Coordinates(x, y)))));
            }
            output.add(stringBuilder.toString());
        }
        return output;
    }

    public long countByType(AcreType type) {
        return areaMap.values().stream()
                .filter(t -> t==type)
                .count();
    }

    public long getResourceValue() {
        return countByType(TREE) * countByType(LUMBER_YARD);
    }
}
