package net.konzult.adventcalendar2018.day15;

import net.konzult.adventcalendar2018.day13.Coordinates;

public class Route {
    private final Coordinates firstStep;
    private final int distance;

    Route(Coordinates firstStep, int distance) {
        this.firstStep = firstStep;
        this.distance = distance;
    }

    public Coordinates getFirstStep() {
        return firstStep;
    }

    public int getDistance() {
        return distance;
    }
}