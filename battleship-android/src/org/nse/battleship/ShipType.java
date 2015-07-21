package org.nse.battleship;

/**
 * Created by Tom on 21.07.2015.
 */
public enum ShipType {
    CARRIER(1,5,"shipCarrier"),
    CRUISER(2,4,"shipCruiser"),
    GUNBOAT(3,3,"shipGunbboat"),
    SUBMARINE(4,3,"shipSubmarine"),
    SPEEDBOAT(5,2,"shipSpeedBoat");

    private final int type;
    private final int size;
    private final String name;

    ShipType(int type, int size, String name) {
        this.type = type;
        this.size = size;
        this.name = name;
    }

    public int getType() {
        return this.type;
    }

    public int getSize() {
        return this.size;
    }

    public String getName() {
        return this.name;
    }
}
