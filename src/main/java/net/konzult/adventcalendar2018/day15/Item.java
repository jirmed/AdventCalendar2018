package net.konzult.adventcalendar2018.day15;

import net.konzult.adventcalendar2018.day13.Coordinates;

import java.util.Objects;

public class Item implements Comparable<Item> {
    private final ItemType type;
    private Coordinates position;

    public Item(ItemType type, Coordinates position) {
        this.type = type;
        this.position = position;
    }


    @Override
    public int compareTo(Item o) {
        return position.compareTo(o.position);
    }

    public ItemType getType() {
        return type;
    }

    public Coordinates getPosition() {
        return position;
    }

    public void setPosition(Coordinates position) {
        this.position = position;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Item item = (Item) o;
        return type == item.type &&
                position.equals(item.position);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, position);
    }

    @Override
    public String toString() {
        return "Item{" +
                "type=" + type +
                ", position=" + position +
                '}';
    }
}
