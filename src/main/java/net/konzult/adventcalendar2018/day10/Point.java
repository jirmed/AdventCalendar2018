package net.konzult.adventcalendar2018.day10;

import java.util.Objects;

public class Point {
    private int x,y, velocityX, velocityY;

    public Point(int x, int y, int velocityX, int velocityY) {
        this.x = x;
        this.y = y;
        this.velocityX = velocityX;
        this.velocityY = velocityY;
    }

    public void step() {
        x += velocityX;
        y += velocityY;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x &&
                y == point.y &&
                velocityX == point.velocityX &&
                velocityY == point.velocityY;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y, velocityX, velocityY);
    }

    @Override
    public String toString() {
        return "Point{" +
                "x=" + x +
                ", y=" + y +
                ", velocityX=" + velocityX +
                ", velocityY=" + velocityY +
                '}';
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getVelocityX() {
        return velocityX;
    }

    public int getVelocityY() {
        return velocityY;
    }
}
