package net.konzult.adventcalendar2018.day13;

import java.util.Objects;

import static net.konzult.adventcalendar2018.day13.TrackType.*;

public class Cart {
    private static TrackType[] TURN_SCHEMA = {LEFT, STRAIGHT, RIGHT};
    private final int id;
    private Coordinates position;
    private Coordinates direction;

    private int nextTurnIndex = 0;

    public Cart(int id, Coordinates position, Coordinates direction) {
        this.id = id;
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

    public Cart tick(TrackType trackTypeAhead) {
        position = position.add(direction);
        switch (trackTypeAhead) {
            case LEFT:
            case RIGHT:
            case POSITIVE_SWAP:
            case NEGATIVE_SWAP:
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
            case POSITIVE_SWAP:
                direction = Coordinates.of(direction.getY() , direction.getX() );
                break;
            case NEGATIVE_SWAP:
                direction = Coordinates.of(direction.getY() *-1 , direction.getX() *-1);
                break;
            case LEFT:
                direction = Coordinates.of(direction.getY() , direction.getX()*-1 );
                break;
            case RIGHT:
                direction = Coordinates.of(direction.getY() *-1 , direction.getX() );
                break;

        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cart cart = (Cart) o;
        return id == cart.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Cart{" +
                "id=" + id +
                ", position=" + position +
                ", direction=" + direction +
                '}';
    }
}
