package net.konzult.adventcalendar2018.day11;

import java.util.Objects;

public class Battery {

    private final int serialNo;
    private final int batterySize;

    private int[][] cells;


    public Battery(int batterySize, int serialNo) {
        cells = new int[batterySize][batterySize];
        this.serialNo = serialNo;
        this.batterySize = batterySize;
    }

    public void init() {
        for (int x = 1; x <= batterySize; x++) {
            for (int y = 1; y <= batterySize; y++) {
                cells[x - 1][y - 1] = getCellPowerLevel(x, y);
            }
        }
    }

    public int getCellPowerLevel(int x, int y) {
        int rackId = x + 10;
        int powerLevelBase = (rackId * y + serialNo) * rackId;
        return powerLevelBase / 100 % 10 - 5;
    }

    public int[][] getCells() {
        return cells;
    }

    public int getSerialNo() {
        return serialNo;
    }

    public int getBatterySize() {
        return batterySize;
    }

    @Override
    public String toString() {
        return "Battery{" +
                "serialNo=" + serialNo +
                ", batterySize=" + batterySize +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Battery battery = (Battery) o;
        return serialNo == battery.serialNo &&
                batterySize == battery.batterySize;
    }

    @Override
    public int hashCode() {
        return Objects.hash(serialNo, batterySize);
    }

    public Grid getMaxPowerGrid(int gridSize) {
        Grid maxGrid = null;
        int maxPower = Integer.MIN_VALUE;
        for (int x = 1; x <= batterySize - gridSize + 1; x++) {
            for (int y = 1; y <= batterySize - gridSize + 1; y++) {
                Grid grid = new Grid(x,y,gridSize);
                int power = grid.getPowerLevel();
                if (power > maxPower) {
                    maxGrid = grid;
                    maxPower = power;
                }
            }
        }
        return maxGrid;
    }

    public Grid getMaxPowerGrid() {
        int maxPowerLevel = Integer.MIN_VALUE;
        Grid maxGrid = null;
        for (int gridSize = 1; gridSize <= batterySize; gridSize++) {
            Grid grid = getMaxPowerGrid(gridSize);
            int powerLevel = grid.getPowerLevel();
            if (maxPowerLevel < powerLevel) {
                maxPowerLevel = powerLevel;
                maxGrid = grid;
            }
        }
        return maxGrid;
    }

    public class Grid {
        private final int x, y, size;

        public Grid(int x, int y, int size) {
            this.x = x;
            this.y = y;
            this.size = size;
        }

        public int getPowerLevel() {
            int totalPower = 0;
            for (int currentX = x; currentX < x + size; currentX++) {
                for (int currentY = y; currentY < y + size; currentY++) {
                    totalPower += cells[currentX - 1][currentY - 1];
                }

            }
            return totalPower;
        }


        public int getX() {
            return x;
        }

        public int getY() {
            return y;
        }

        public int getSize() {
            return size;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Grid grid = (Grid) o;
            return x == grid.x &&
                    y == grid.y &&
                    size == grid.size;
        }

        @Override
        public int hashCode() {
            return Objects.hash(x, y, size);
        }

        @Override
        public String toString() {
            return "Grid{" +
                    "x=" + x +
                    ", y=" + y +
                    ", size=" + size +
                    '}';
        }
    }
}
