package net.konzult.adventcalendar2018.day6;

import org.checkerframework.checker.units.qual.C;

import java.util.Comparator;
import java.util.List;

public class World {
    private final List<Coordinate> coordinateList;
    private final int minX, maxX, minY, maxY;

    public World(List<Coordinate> coordinateList) {
        this.coordinateList = coordinateList;
        minX = coordinateList.stream().mapToInt(Coordinate::getX).min().getAsInt();
        minY = coordinateList.stream().mapToInt(Coordinate::getY).min().getAsInt();
        maxX = coordinateList.stream().mapToInt(Coordinate::getX).max().getAsInt();
        maxY = coordinateList.stream()
                .mapToInt(Coordinate::getY).max().getAsInt();
    }

    public Coordinate getMaxAreaCoordinate() {
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                int minDistance = Integer.MAX_VALUE;
                Coordinate closestCoordinate = null;
                boolean border = false;
                for (Coordinate coordinate : coordinateList) {
                    int distance = getDistance(x, y, coordinate);
                    if (distance == minDistance) {
                        border = true;
                    } else if (distance < minDistance) {
                        border = false;
                        minDistance = distance;
                        closestCoordinate = coordinate;
                    }
                }
                if (!border) {
                    if (x == minX ||
                            x == maxX ||
                            y == minY ||
                            y == maxY) {
                        closestCoordinate.setInfinite(true);
                    }
                    closestCoordinate.incArea();
                }
            }
        }
        return coordinateList
                .stream()
                .filter(coordinate -> !coordinate.isInfinite())
                .max(Comparator.comparing(Coordinate::getArea)).get();
    }

    public int getAreaWithinTotalDistance(int maxDistance) {
        int area = 0;
        for (int x = minX; x <= maxX; x++) {
            for (int y = minY; y <= maxY; y++) {
                int distance = 0;
                for (Coordinate coordinate : coordinateList) {
                    distance += this.getDistance(x, y, coordinate);
                    if (distance >= maxDistance)
                        break;
                }
                if (distance < maxDistance) area++;
            }
        }
        return area;
    }

    private int getDistance(int x, int y, Coordinate coordinate) {
        return Math.abs(coordinate.getX() - x) + Math.abs(coordinate.getY() - y);
    }
}
