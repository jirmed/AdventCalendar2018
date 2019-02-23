package net.konzult.adventcalendar2018.day13;

import net.konzult.adventcalendar2018.day6.Coordinate;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class Coordinates implements Comparable<Coordinates> {
    public static final Coordinates ZERO = new Coordinates(0,0);
    public static final Coordinates LEFT = new Coordinates(-1,0);
    public static final Coordinates RIGHT = new Coordinates(1,0);
    public static final Coordinates UP = new Coordinates(0,-1);
    public static final Coordinates DOWN = new Coordinates(0,1);

    public static final List<Coordinates> ORTOGONAL_NEIGHBOURS =
            Arrays.asList(UP, DOWN, LEFT, RIGHT);


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

    public Coordinates down() {
        return new Coordinates(x, y + 1);
    }

    public Coordinates up() {
        return new Coordinates(x, y - 1);
    }


    public Coordinates left() {
        return new Coordinates(x - 1, y);
    }

    public Coordinates right() {
        return new Coordinates(x + 1, y);
    }

    public Coordinates clone()  {
        return new Coordinates(x,y);
    }
}
