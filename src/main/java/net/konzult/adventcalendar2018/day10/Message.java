package net.konzult.adventcalendar2018.day10;

import net.konzult.adventcalendar2018.FileParser;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Message {

    public static final int MAX_WIDTH = 132;
    public static final int MAX_HEIGHT = 80;
    public static final int BORDER = 2;
    private final List<Point> points;
    private int minute = 0;

    public Message(List<Point> points) {
        this.points = points;
    }

    public static Message readMessageFile(String fileName) throws IOException {
        List<Point> points = new ArrayList<>();
        List<String> strings = FileParser.readStringListFile(fileName);
        for (String s : strings) {
            points.add(parsePoint(s));
        }
        return new Message(points);

    }

    private static Point parsePoint(String s) {
        Pattern pattern = Pattern.compile("^position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>$");
        Matcher matcher = pattern.matcher(s);
        if (matcher.find())
            return new Point(Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)),
                    Integer.parseInt(matcher.group(3)),
                    Integer.parseInt(matcher.group(4)));
        else throw new IllegalArgumentException("Invalid format");
    }

    public void step() {
        minute++;
        for (Point point : points) {
            point.step();
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public Rectangle getRectangle() {
        int minX = points.stream().min(Comparator.comparing(Point::getX)).get().getX();
        int minY = points.stream().min(Comparator.comparing(Point::getY)).get().getY();
        int maxX = points.stream().max(Comparator.comparing(Point::getX)).get().getX();
        int maxY = points.stream().max(Comparator.comparing(Point::getY)).get().getY();
        return new Rectangle(minX, minY, maxX - minX, maxY - minY);
    }

    public List<String> render() {
        ArrayList<String> output = new ArrayList<>();
        Rectangle rectangle = getRectangle();
        if (rectangle.getWidth() > MAX_WIDTH - BORDER || rectangle.getHeight() > MAX_HEIGHT - BORDER)
            return output;
        for (int y = rectangle.getY() - BORDER; y <= rectangle.getY() + rectangle.getHeight() + BORDER; y++) {
            StringBuilder line = new StringBuilder();
            for (int i = 0; i <= rectangle.getWidth() + 2 * BORDER; i++) {
                line.append('.');
            }
            int y1 = y;
            points.stream()
                    .filter(point -> point.getY() == y1)
                    .forEach(point -> {
                        line.setCharAt(point.getX() - rectangle.getX() + BORDER, 'X');
                    });
            output.add(line.toString());
        }
        return output;
    }

    public List<String> getSmallestMessage() {
        int minArea = Integer.MAX_VALUE;
        List<String> output = render();
        while (true) {
            step();
            int area = getRectangle().getArea();
            if (area > minArea && output.size() > 0) break;
            minArea = area;
            output = render();
        }
        return output;
    }

    public int getMinute() {
        return minute;
    }

    public class Rectangle {
        private final int x, y, width, height;

        private Rectangle(int x, int y, int width, int height) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
        }

        @Override
        public String toString() {
            return "Rectangle{" +
                    "x=" + x +
                    ", y=" + y +
                    ", width=" + width +
                    ", height=" + height +
                    '}';
        }

        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getWidth() {
            return width;
        }

        public int getHeight() {
            return height;
        }

        public int getArea() {
            return width * height;
        }
    }
}
