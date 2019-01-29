package net.konzult.adventcalendar2018;

import java.util.*;

public class Fabric {
    private final int width, height;
    private final Set<Tile>
            duplicateTiles = new HashSet<>();
    private final Map<Tile, Rectangle>
            usedTiles = new HashMap<>();
    private final Set<Rectangle>
            wholeRectangles = new HashSet<>();

    public Fabric(int width, int height) {
        this.width = width;
        this.height = height;
    }

    public void cutRectangle(Rectangle rectangle) {
        Boolean whole = true;
        for (int x = rectangle.getFromLeft();
             x < rectangle.getFromLeft() + rectangle.getWidth();
             x++) {
            for (int y = rectangle.getFromTop();
                 y < rectangle.getFromTop() + rectangle.getHeight();
                 y++) {
                Tile tile = new Tile(x, y);
                Rectangle previousRectangle = usedTiles.get(tile);
                if (previousRectangle == null) {
                    usedTiles.put(tile, rectangle);
                } else {
                    whole = false;
                    if (duplicateTiles.add(tile)) {
                        wholeRectangles.remove(previousRectangle);
                    }
                }
            }

        }
        if (whole) wholeRectangles.add(rectangle);
    }

    public void cutRectangles(List<Rectangle> rectangles) {
        for (int i = 0; i < rectangles.size(); i++) {
            Rectangle rectangle = rectangles.get(i);
            this.cutRectangle(rectangle);
        }
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Map<Tile, Rectangle> getUsedTiles() {
        return usedTiles;
    }

    public Set<Rectangle> getWholeRectangles() {
        return wholeRectangles;
    }

    public Set<Tile> getDuplicateTiles() {
        return duplicateTiles;
    }
}
