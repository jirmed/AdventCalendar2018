package net.konzult.adventcalendar2018.day15;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.*;
import java.util.stream.Collectors;

import static net.konzult.adventcalendar2018.day15.ItemType.ELF;
import static net.konzult.adventcalendar2018.day15.ItemType.WALL;

public class BattleField {
    public static final int DEFAULT_ATTACK = 3;
    public static final int MAX_ROUNDS = 10000;
    private final Map<Coordinates, Item> fieldMap;
    private int elfAttack = DEFAULT_ATTACK;

    public BattleField(Map<Coordinates, Item> fieldMap) {
        this.fieldMap = fieldMap;
    }

    public static BattleField parse(List<String> strings) {
        Map<Coordinates, Item> map = new TreeMap<>();
        for (int y = 0; y < strings.size(); y++) {
            String s = strings.get(y);
            for (int x = 0; x < s.length(); x++) {
                Coordinates coordinates = new Coordinates(x, y);
                Item item = null;
                switch (s.charAt(x)) {
                    case 'E':
                        item = new Warrior(ItemType.ELF, coordinates);
                        break;
                    case 'G':
                        item = new Warrior(ItemType.GOBLIN, coordinates);
                        break;
                    case '#':
                        item = new Item(WALL, coordinates);
                        break;
                }
                if (item != null)
                    map.put(coordinates, item);
            }
        }
        return new BattleField(map);
    }

    public Map<Coordinates, Item> getFieldMap() {
        return fieldMap;
    }

    @Override
    public String toString() {
        return "BattleField{" +
                "fieldMap=" + fieldMap +
                '}';
    }

    public Move nextStep(Warrior warrior) {
        Map<Coordinates, Route> distanceMap = new TreeMap<>();
        int distance = 0;
        distanceMap.put(warrior.getPosition(), new Route(null, distance++));
        Map<Coordinates, Route> distanceRouteMap = new TreeMap<>(distanceMap);
        while (!distanceRouteMap.isEmpty()) {
            for (Map.Entry<Coordinates, Route> entry : distanceRouteMap.entrySet()) {
                Coordinates position = entry.getKey();
                Route route = entry.getValue();
                Route newRoute = getNewRoute(distance, position, route);
                Warrior enemy = null;
                for (int y = -1; y <= 1; y++) {
                    for (int x = -1; x <= 1; x++) {
                        if (x == 0 ^ y == 0) {
                            Coordinates newPosition = new Coordinates(position.getX() + x, position.getY() + y);
                            Item item = fieldMap.get(newPosition);
                            if (item == null) {
                                distanceMap.putIfAbsent(newPosition, newRoute);
                            } else if (item.getType() == warrior.getEnemyType())
                                if (distance > 2)
                                    return new Move(newRoute.getFirstStep(), null);
                                else if (enemy == null || enemy.getHitPoints() > ((Warrior) item).getHitPoints())
                                    enemy = (Warrior) item;
                        }
                    }
                }
                if (enemy != null) return
                        new Move((distance == 2 ? newRoute.getFirstStep() : null), enemy);
            }
            distanceRouteMap = getDistanceRouteMap(distanceMap, distance++);
        }
        return null;
    }

    private Route getNewRoute(int distance, Coordinates position, Route route) {
        return new Route((distance == 2 ? position : route.getFirstStep()), distance);
    }

    private Map<Coordinates, Route> getDistanceRouteMap(Map<Coordinates, Route> distanceMap, int distance) {
        return new TreeMap<>(distanceMap.entrySet().stream().filter(entry -> entry.getValue().getDistance() == distance)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)));
    }

    public boolean playWarrior(Warrior warrior) {
        if (warrior.getHitPoints() <= 0) return false;
        Move move = nextStep(warrior);
        if (move == null) return false;
        if (move.moveTo != null) {
            fieldMap.remove(warrior.getPosition());
            warrior.setPosition(move.moveTo);
            fieldMap.put(warrior.getPosition(), warrior);
        }
        if (move.enemy != null) {
            hitWarrior(move.enemy);
        }
        return true;
    }

    private void hitWarrior(Warrior warrior) {
        if (warrior.hit((warrior.getType() != ELF ? elfAttack : DEFAULT_ATTACK)) <= 0)
            fieldMap.remove(warrior.getPosition());
    }

    public int play(int maxRounds) {
        boolean completed = false;
        int round = 0;
        for (round = 0; round < maxRounds && !completed; round++) {
            completed = true;
            List<Warrior> warriors = getWarriors();
            for (Item warrior : warriors) {
                if (playWarrior((Warrior) warrior))
                    completed = false;
            }
        }
        return (completed ? round - 2 : round - 1);
    }

    List<Warrior> getWarriors() {
        return fieldMap.values().stream()
                .filter(item -> item instanceof Warrior)
                .map(item -> (Warrior) item)
                .collect(Collectors.toList());
    }

    List<Warrior> getWarriors(ItemType type) {
        return fieldMap.values().stream()
                .filter(item -> item.getType() == type && item instanceof Warrior)
                .map(item -> (Warrior) item)
                .collect(Collectors.toList());
    }


    public class Move {
        final Coordinates moveTo;
        final Warrior enemy;

        public Move(Coordinates moveTo, Warrior enemy) {
            this.moveTo = moveTo;
            this.enemy = enemy;
        }

        @Override
        public String toString() {
            return "Move{" +
                    "moveTo=" + moveTo +
                    ", enemy=" + enemy +
                    '}';
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Move move = (Move) o;
            return Objects.equals(moveTo, move.moveTo) &&
                    Objects.equals(enemy, move.enemy);
        }

        @Override
        public int hashCode() {
            return Objects.hash(moveTo, enemy);
        }
    }

    public int getTotalHitpoints() {
        return getWarriors().stream().mapToInt(warrior -> warrior.getHitPoints()).sum();
    }

    public int getElfAttack() {
        return elfAttack;
    }

    public void setElfAttack(int elfAttack) {
        this.elfAttack = elfAttack;
    }

    public static int getMinElfAttackToSurviveAll(List<String> strings) {
        int attackScore = DEFAULT_ATTACK;
        int originalElfCount = 0;
        int endElfCount = 0;
        do {
            BattleField field = BattleField.parse(strings);
            originalElfCount = field.getWarriors(ELF).size();
            field.setElfAttack(attackScore++);
            field.play(MAX_ROUNDS);
            endElfCount = field.getWarriors(ELF).size();
        } while (endElfCount < originalElfCount);
        return attackScore - 1;
    }
}
