package net.konzult.adventcalendar2018;

import java.util.Objects;

public class Rectangle {
    private final int
            id, fromLeft, fromTop, width, height;

    public Rectangle(int id, int fromLeft, int fromTop, int width, int height) {
        this.id = id;
        this.fromLeft = fromLeft;
        this.fromTop = fromTop;
        this.width = width;
        this.height = height;
    }

    public int getFromLeft() {
        return fromLeft;
    }

    public int getFromTop() {
        return fromTop;
    }

    public int getWidth() {
        return width;
    }


    public int getHeight() {
        return height;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Rectangle rectangle = (Rectangle) o;
        return id == rectangle.id &&
                fromLeft == rectangle.fromLeft &&
                fromTop == rectangle.fromTop &&
                width == rectangle.width &&
                height == rectangle.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, fromLeft, fromTop, width, height);
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "id=" + id +
                ", fromLeft=" + fromLeft +
                ", fromTop=" + fromTop +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
