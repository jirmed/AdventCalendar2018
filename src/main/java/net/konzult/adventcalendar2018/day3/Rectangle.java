package net.konzult.adventcalendar2018.day3;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.List;
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

    public static List<Rectangle> readRectangleListFile(String fileName) throws IOException {
        List result = new ArrayList<>();
        List<String> strings = FileParser.readStringListFile(fileName);
        for (int i = 0; i < strings.size(); i++) {
            String s = strings.get(i);
            result.add(parseRectangle(s));
        }
        return result;
    }

    public static Rectangle parseRectangle(String stringToParse) {
        if (!stringToParse.matches("^#\\d+\\s+@\\s+\\d+,\\d+:\\s+\\d+x\\d+$"))
            throw new InvalidParameterException("Invalid format");
        String s = stringToParse.replaceAll("[#@:]", "");
        String[] parts = s.split("\\s+");
        String[] corner = parts[1].split(",");
        String[] size = parts[2].split("x");
        return new Rectangle(
                Integer.parseInt(parts[0]),
                Integer.parseInt(corner[0]),
                Integer.parseInt(corner[1]),
                Integer.parseInt(size[0]),
                Integer.parseInt(size[1]));
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
