package net.konzult.adventcalendar2018.day13;

import net.konzult.adventcalendar2018.day6.Coordinate;

import java.util.Comparator;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    private final int x, y;

    public Coordinates(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Coordinates that = (Coordinates) o;
        return x == that.x &&
                y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Coordinates{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    public Coordinates add(Coordinates direction) {
        return new Coordinates(x + direction.x, y + direction.y);
    }


    public static Coordinates of(int x, int y) {
        return new Coordinates(x, y);
    }

    @Override
    public int compareTo(Coordinates o) {
        if (y == o.y) return x - o.x;
        else return y - o.y;
    }

    public Coordinates bellow() {
        return new Coordinates(x,y+1);
    }

    public Coordinates left() {
        return new Coordinates(x-1,y);
    }
    public Coordinates right() {
        return new Coordinates(x+1,y);
    }
}
