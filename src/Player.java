import processing.core.PApplet;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Random;
import processing.core.PImage;

public class Player extends Character implements Moveable {
    private boolean hasKey;

    public Player(Room currentRoom) {
        super(currentRoom, "PLAYER");
        hasKey = false;
    }

    public boolean hasKey() {
        return hasKey;
    }

    public void obtainKey() {
        hasKey = true;
    }

    public boolean changeRoom(Room destination) {
        if (canMoveTo(destination)) {
            super.setCurrentRoom(destination);
            return true;
        }
        else {
            return false;
        }
    }

    public boolean canMoveTo(Room destination) {
        return getCurrentRoom().isAdjacent(destination);
    }
    
    public boolean teleport() {
        return getCurrentRoom() instanceof PortalRoom;
    }

    public boolean isPortalNearby() {
        ArrayList<Room> adj = getAdjacentRooms();
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i) instanceof PortalRoom) {
                return true;
            }
        }

        return false;
    }

    public boolean isTreasureNearby() {
        ArrayList<Room> adj = getAdjacentRooms();
        for (int i = 0; i < adj.size(); i++) {
            if (adj.get(i) instanceof TreasureRoom) {
                return true;
            }
        }

        return false;
    }

    public boolean isDragonNearby(Dragon d) {
        return super.getAdjacentRooms().contains(d.getCurrentRoom());
    }
}
