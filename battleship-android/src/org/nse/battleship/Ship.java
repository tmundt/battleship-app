package org.nse.battleship;

/**
 * Created by Tom on 21.07.2015.
 */
public class Ship {
    private String position;
    private int size;
    private int type;
    private String name;
    private boolean positioned;
    private boolean selected;
    private ShipType shipType;
    private int startX;
    private int startY;

    public Ship(ShipType shipType) {
        this.size = shipType.getSize();
        this.type = shipType.getType();
        this.name = shipType.getName();
        this.positioned = false;
        this.shipType = shipType;
        this.startX = 0;
        this.startY = 0;
    }

    public String Position() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public int getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public boolean isPositioned() {
        return positioned;
    }

    public boolean isSelected() {
        return selected;
    }

    public ShipType getShipType() {
        return shipType;
    }

    public int getStartX() {
        return startX;
    }

    public int getStartY() {
        return startY;
    }
}
