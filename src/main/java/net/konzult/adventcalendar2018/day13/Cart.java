package net.konzult.adventcalendar2018.day13;

import static net.konzult.adventcalendar2018.day13.TrackType.*;

public class Cart {
    private static TrackType[] TURN_SCHEMA = {LEFT, STRAIGHT, RIGHT};
    private Coordinates position;
    private Coordinates direction;

    private int nextTurnIndex = 0;

    public Cart(Coordinates position, Coordinates direction) {
        this.position = position;
        this.direction = direction;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    public Coordinates getDirection() {
        return direction;
    }

    public void setDirection(Coordinates direction) {
        this.direction = direction;
    }

    @Override
    public String toString() {
        return "Cart{" +
                "position=" + position +
                ", direction=" + direction +
                '}';
    }

    public Cart tick(TrackType trackTypeAhead) {
        position.add(direction);
        switch (trackTypeAhead) {
            case LEFT:
            case RIGHT:
                turn(trackTypeAhead);
                break;

            case CROSSING:
                turn(TURN_SCHEMA[nextTurnIndex++]);
                if (nextTurnIndex == TURN_SCHEMA.length)
                    nextTurnIndex = 0;
                break;
        }
        return this;
    }

    private void turn(TrackType turnType) {
        switch (turnType) {
            case LEFT:
                direction = Coordinates.of(direction.getY(), direction.getX() * -1);
                break;
            case RIGHT:
                direction = Coordinates.of(direction.getY() * -1, direction.getX());
                break;
        }
    }
}
