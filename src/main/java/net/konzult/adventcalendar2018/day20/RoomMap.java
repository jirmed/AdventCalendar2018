package net.konzult.adventcalendar2018.day20;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;

public class RoomMap {

    private static final Map<Direction, Coordinates> DirectionToCoordinatesMap =
            Map.of(Direction.E, Coordinates.RIGHT,
                    Direction.W, Coordinates.LEFT,
                    Direction.N, Coordinates.UP,
                    Direction.S, Coordinates.DOWN);

    Set<Coordinates> doorsEast = new TreeSet<>();
    Set<Coordinates> doorsSouth = new TreeSet<>();
    private Map<Coordinates, Integer> distanceMap;

    static List<String> splitter(String input, char separator) {
        List<String> result = new ArrayList<>();
        int level = 0;
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < input.length(); i++) {
            char c = input.charAt(i);
            if (c == '|' && level == 0) {
                result.add(stringBuilder.toString());
                stringBuilder = new StringBuilder();
            } else {
                stringBuilder.append(c);
                if (c == '(') level++;
                else if (c == ')' && level > 0) level--;
            }
        }
        result.add(stringBuilder.toString());
        return result;
    }

    private static int findClosingParen(String text, int openPos) {
        int closePos = openPos;
        int counter = 1;
        while (counter > 0 && closePos < text.length() - 1) {
            char c = text.charAt(++closePos);
            if (c == '(') {
                counter++;
            } else if (c == ')') {
                counter--;
            }
        }
        return closePos;
    }


    void traceAllPaths(Coordinates position, String path) {
        Coordinates pos = position.clone();
        for (int i = 0; i < path.length(); i++) {
            String command = path.substring(i, i + 1);
            if ("NESW".contains(command)) {
                pos = goThroughDoorAndCreate(pos, Direction.valueOf(command));
            } else if (command.equals("(")) {
                int endIndex = findClosingParen(path, i) - 1;
                List<String> alternatives = splitter(path.substring(i + 1, endIndex + 1), '|');
                String reminder = path.substring(endIndex + 2);
                if (alternatives.get(alternatives.size() - 1).equals("")) {
                    traceAllPaths(pos, reminder);
                    for (int j = 0; j < alternatives.size() - 1; j++) {
                        traceAllPaths(pos, alternatives.get(j));
                    }
                } else
                    for (String alternative : alternatives) {
                        traceAllPaths(pos, alternative + reminder);
                    }
                break;
            }
        }
    }

    private Coordinates goThroughDoorAndCreate(Coordinates room, Direction direction) {
        switch (direction) {
            case S:
                doorsSouth.add(room);
                return room.down();
            case E:
                doorsEast.add(room);
                return room.right();
            case N:
                doorsSouth.add(room.up());
                return room.up();
            case W:
                doorsEast.add(room.left());
                return room.left();
        }

        return room;
    }

    private boolean isDoor(Coordinates position, Direction direction) {
        switch (direction) {
            case E:
                return doorsEast.contains(position);
            case S:
                return doorsSouth.contains(position);
            case W:
                return doorsEast.contains(position.left());
            case N:
                return doorsSouth.contains(position.up());
        }
        return false;
    }

    private void populateDistanceMap(Coordinates position, int distance) {
        if (distanceMap.putIfAbsent(position, distance) != null) return;
        for (Direction direction : Direction.values()) {
            if (isDoor(position, direction))
                populateDistanceMap(position.add(DirectionToCoordinatesMap.get(direction)), distance + 1);
        }
    }

    void populateDistanceMap() {
        distanceMap = new TreeMap<>();
        populateDistanceMap(Coordinates.ZERO,0);
    }

    int getMaxDistance() {
        return distanceMap.values().stream().max(Integer::compareTo).get();
    }

    long countDoorsInDistanceAtLeast(int distance) {
        return distanceMap.values().stream()
                .filter(d -> d>=distance)
                .count();
    }

}
