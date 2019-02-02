package net.konzult.adventcalendar2018.day6;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Coordinate {
    private final int x, y;
    private boolean infinite = false;
    private int area = 0;

    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void incArea() {
        area++;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setInfinite(boolean infinite) {
        this.infinite = infinite;
    }

    public boolean isInfinite() {
        return infinite;
    }

    public int getArea() {
        return area;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinate that = (Coordinate) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinate{" +
                "x=" + x +
                ", y=" + y +
                ", infinite=" + infinite +
                ", area=" + area +
                '}';
    }

    public static List<Coordinate> readCoordinateListFile(String fileName) throws IOException {
        List<String> strings = FileParser.readStringListFile(fileName);

        List<Coordinate> coordinates = new ArrayList<>();
        for (String coordinateString : strings) {
            String[] parts = coordinateString.split(", ");
            coordinates.add(new Coordinate(Integer.parseInt(parts[0]),
                    Integer.parseInt(parts[1])));
        }
        return coordinates;
    }
}
