package net.konzult.adventcalendar2018.day15;

import net.konzult.adventcalendar2018.day13.Coordinates;


public class Warrior extends Item {

    private int hitPoints;

    public Warrior(ItemType type, Coordinates position) {
        super(type, position);
        hitPoints = 200;
    }

    public int getHitPoints() {
        return hitPoints;
    }

    public void setHitPoints(int hitPoints) {
        this.hitPoints = hitPoints;
    }

    public ItemType getEnemyType() {
        return (getType() == ItemType.ELF ? ItemType.GOBLIN : ItemType.ELF);
    }

    public int hit(int hitPoints) {
        this.hitPoints -= hitPoints;
        return this.hitPoints;
    }

    @Override
    public String toString() {
        return "Warrior{" +
                "hitPoints=" + hitPoints +
                "} " + super.toString();
    }
}
