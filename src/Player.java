import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Player extends Character implements Moveable {
    private boolean hasKey;

    /**
     * create new player
     * 
     * @param currentRoom room to start in
     */
    public Player(Room currentRoom) {
        super(currentRoom, "PLAYER");
        hasKey = false;
    }

    /**
     * getter
     * 
     * @return boolean key
     */
    public boolean hasKey() {
        return hasKey;
    }

    /**
     * setter
     */
    public void obtainKey() {
        hasKey = true;
    }

    /**
     * changes player room if they can move there
     * 
     * @param destination room to move to
     * @return boolean if moving was successful
     */
    public boolean changeRoom(Room destination) {
        if (canMoveTo(destination)) {
            super.setCurrentRoom(destination);
            return true;
        } else {
            return false;
        }
    }

    /**
     * checks if plaayer can move
     * 
     * @param destination room to check
     * @return boolean result
     */
    public boolean canMoveTo(Room destination) {
        return getCurrentRoom().isAdjacent(destination);
    }

    /**
     * checks if player can teleport
     * 
     * @return boolean result
     */
    public boolean teleport() {
        return getCurrentRoom() instanceof PortalRoom; // TODO finish teleportation?
    }

    /**
     * checks if portal is nearby
     * 
     * @return boolean result
     */
    public boolean isPortalNearby() {
        ArrayList<Room> adj = getAdjacentRooms();
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i) instanceof PortalRoom) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if treasure is nearby
     * 
     * @return boolean result
     */
    public boolean isTreasureNearby() {
        ArrayList<Room> adj = getAdjacentRooms();
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i) instanceof TreasureRoom) {
                return true;
            }
        }

        return false;
    }

    /**
     * checks if dragon is nearby
     * 
     * @param d dragon to check for
     * @return boolean result
     */
    public boolean isDragonNearby(Dragon d) {
        return super.getAdjacentRooms().contains(d.getCurrentRoom());
    }
}
